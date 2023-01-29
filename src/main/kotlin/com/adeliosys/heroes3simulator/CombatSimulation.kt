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

        val winner: CreatureStack = if (stack1.isAlive()) stack1 else stack2
        println("Combat simulation is over: winner is ${winner.creature.name} (${winner.quantity} left with ${winner.creature.currentHealth} health)")
    }

    /**
     * Is the simulation over.
     */
    private fun isOver() = !stack1.isAlive() || !stack2.isAlive()
}
