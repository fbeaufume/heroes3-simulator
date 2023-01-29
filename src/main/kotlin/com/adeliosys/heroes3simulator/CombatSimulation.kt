package com.adeliosys.heroes3simulator

class CombatSimulation(val stack1: CreatureStack, val stack2: CreatureStack) {

    private var round = 1;

    /**
     * Run the simulation.
     */
    fun run() {
        println("Starting combat simulation of ${stack1.quantity} ${stack1.creature.name} versus ${stack2.quantity} ${stack2.creature.name}")

        while (true) {
            if (isOver()) break;
            stack1.attack(stack2, round)

            if (isOver()) break;
            stack2.attack(stack1, round)

            round++
        }

        // TODO FBE display the winner
        println("Combat simulation is over: ${stack1.creature.name} (x${stack1.quantity}, ${stack1.creature.currentHealth} health) and ${stack2.creature.name} (x${stack2.quantity}, ${stack2.creature.currentHealth} health)")
    }

    /**
     * Is the simulation over.
     */
    private fun isOver() = !stack1.isAlive() || !stack2.isAlive()
}
