package com.adeliosys.heroes3simulator

import kotlin.math.max

/**
 * Compute the number of creatures needed to defeat a certain stack of creatures.
 * The amount is computed by dichotomy using combat simulations with a varying amount of creatures
 */
class ValueSimulation(creature1: Creature, initialQuantity1: Int, creature2: Creature, logLevel: Int = 1) :
    BaseSimulation(CreatureStack(creature1, initialQuantity1), CreatureStack(creature2, 0), logLevel) {

    /**
     * The low quantity used by the dichotomy.
     */
    private var lowQuantity: Int? = null

    /**
     * The high quantity used by the dichotomy.
     */
    private var highQuantity: Int? = null

    /**
     * The current combat number.
     */
    private var combat = 0

    /**
     * Run the simulation.
     */
    override fun runOneStep(): Boolean {
        // Run the next combat simulation
        combat++
        stack1.resetQuantity()
        stack2.defineInitialQuantity(computeQuantity())
        val combatSimulation = CombatSimulation(stack1.creature, stack1.initialQuantity, stack2.creature, stack2.initialQuantity, logLevel - 1)
        combatSimulation.run()
        val result = combatSimulation.doesStack1Win()

        // Update the low and high quantities using the combat simulation result
        updateLowAndHighQuantities(stack2.initialQuantity, result)

        return false
    }

    override fun isOver(): Boolean {
        if (lowQuantity != null && highQuantity != null && highQuantity == lowQuantity!! + 1) {
            // This happens when stack 1 is stronger than stack 2
            return true
        }

        if (lowQuantity == null && highQuantity == 1) {
            // This happens when stack 1 is weaker than stack 2
            return true
        }

        return false
    }

    override fun done() {
        log(logLevel, "Value simulation of ${stack1.quantity} ${stack1.creature.name} versus ${stack2.creature.name}: result is $highQuantity")
    }

    /**
     * Compute the quantity needed by the next combat simulation.
     */
    private fun computeQuantity(): Int {
        if (lowQuantity == null && highQuantity == null) {
            return max(stack1.creature.initialHealth * stack1.initialQuantity / stack2.creature.initialHealth, 1)
        }

        if (lowQuantity == null) {
            return highQuantity!! / 2
        }

        if (highQuantity == null) {
            return lowQuantity!! * 2
        }

        return (lowQuantity!! + highQuantity!!) / 2
    }

    /**
     * Update the low and high quantities using the combat simulation result.
     */
    private fun updateLowAndHighQuantities(quantity: Int, result: Boolean) {
        if (result) {
            lowQuantity = quantity
        } else {
            highQuantity = quantity
        }
    }
}