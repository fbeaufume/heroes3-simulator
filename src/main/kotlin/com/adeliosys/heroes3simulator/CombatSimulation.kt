package com.adeliosys.heroes3simulator

import kotlin.system.measureTimeMillis

/**
 * Simulate a combat between two stacks of creatures.
 */
class CombatSimulation(val stack1: CreatureStack, val stack2: CreatureStack, val logLevel: Int = 1) {

    /**
     * The current combat round number.
     */
    private var round = 0;

    /**
     * Run the simulation.
     * Return true if the first stack wins, false otherwise.
     */
    fun run(): Boolean {
        val duration = measureTimeMillis {
            log(logLevel, "Starting combat simulation of ${stack1.quantity} ${stack1.creature.name} versus ${stack2.quantity} ${stack2.creature.name}")

            if (stack1 === stack2) {
                println("Different creature stacks must be used")
                return true
            }

            while (true) {
                round++

                if (isOver()) break;
                stack1.attack(stack2, round, logLevel - 1)

                if (isOver()) break;
                stack2.attack(stack1, round, logLevel - 1)
            }
        }

        val winner: CreatureStack = if (stack1.isAlive()) stack1 else stack2
        log(logLevel, "Combat simulation executed in $duration msec: winner is ${winner.creature.name} (${winner.quantity} left with ${winner.creature.currentHealth} health)")

        return winner === stack1
    }

    /**
     * Is the simulation over.
     */
    private fun isOver() = !stack1.isAlive() || !stack2.isAlive()
}
