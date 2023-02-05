package com.adeliosys.heroes3simulator

/**
 * Simulate a combat between two stacks of creatures.
 */
class CombatSimulation(private val creature1: Creature, initialQuantity1: Int, private val creature2: Creature, initialQuantity2: Int, private val logLevel: Int = 1) {

    /**
     * The stack for creature 1.
     */
    private val stack1 = CreatureStack(creature1, initialQuantity1)

    /**
     * The stack for creature 1.
     */
    private val stack2 = CreatureStack(creature2, initialQuantity2)

    /**
     * The current combat round number.
     */
    private var round = 0;

    /**
     * Run the simulation.
     * Return true if the first stack wins, false otherwise.
     */
    fun run(): Boolean {
        if (creature1 === creature2) {
            println("Different creature instances must be used")
            return true
        }

        while (true) {
            round++

            if (isOver()) break;
            stack1.attack(stack2, round, logLevel - 1)

            if (isOver()) break;
            stack2.attack(stack1, round, logLevel - 1)
        }

        val winner: CreatureStack = if (stack1.isAlive()) stack1 else stack2

        val message = "Combat simulation of ${stack1.initialQuantity} ${stack1.creature.name} versus ${stack2.initialQuantity} ${stack2.creature.name}" +
                ": winner is ${winner.creature.name} (${winner.quantity} left with ${winner.creature.currentHealth} health)"

        log(logLevel, message)

        return winner === stack1
    }

    /**
     * Is the simulation over.
     */
    private fun isOver() = !stack1.isAlive() || !stack2.isAlive()
}
