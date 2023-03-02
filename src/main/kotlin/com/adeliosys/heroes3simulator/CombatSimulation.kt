package com.adeliosys.heroes3simulator

/**
 * Simulate a combat between two stacks of creatures.
 */
class CombatSimulation(creature1: Creature, initialQuantity1: Int, creature2: Creature, initialQuantity2: Int, logLevel: Int = 1) :
    BaseSimulation(CreatureStack(creature1, initialQuantity1), CreatureStack(creature2, initialQuantity2), logLevel) {

    /**
     * The combat winner stack.
     */
    private var winner: CreatureStack = stack1

    /**
     * Run the simulation.
     * Return true if the first stack wins, false otherwise.
     */
    override fun runOneStep(): Boolean {
        stack1.attack(stack2, step, logLevel - 1)

        if (isOver()) return true
        stack2.attack(stack1, step, logLevel - 1)

        return false
    }

    override fun isOver() = !stack1.isAlive() || !stack2.isAlive()

    override fun done() {
        winner = if (stack1.isAlive()) stack1 else stack2

        val message = "Combat simulation of ${stack1.initialQuantity} ${stack1.creature.name} versus ${stack2.initialQuantity} ${stack2.creature.name}" +
                ": winner is ${winner.creature.name} (${winner.quantity} left with ${winner.creature.currentHealth} health)"

        log(logLevel, message)
    }

    /**
     * Return true if stack 1 won the combat.
     */
    fun doesStack1Win(): Boolean = winner === stack1
}
