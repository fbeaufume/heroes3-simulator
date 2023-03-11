package com.adeliosys.heroes3simulator

abstract class BaseSimulation(val logLevel: Int = 1) {

    /**
     * The current simulation step.
     */
    protected var step = 0

    /**
     * Make sure that the quantity is strictly positive.
     */
    fun checkQuantity(stack: CreatureStack) {
        if (stack.quantity <= 0) {
            throw Exception("Quantity must be strictly positive")
        }
    }

    /**
     * Make sure that the creature stacks are different.
     */
    fun checkCreatureStacks(stack1: CreatureStack, stack2: CreatureStack) {
        if (stack1 === stack2) {
            throw Exception("Different creature stack instances must be used")
        }

        if (stack1.creature === stack2.creature) {
            throw Exception("Different creature instances must be used")
        }
    }

    /**
     * Run all steps of the simulation.
     */
    fun run() {
        while (true) {
            if (isOver()) break

            step++

            if (runOneStep()) break
        }

        done()
    }

    /**
     * Run one step of the simulation.
     * Return true if the simulation is over, false otherwise.
     */
    abstract fun runOneStep(): Boolean

    /**
     * Is the simulation over.
     */
    abstract fun isOver(): Boolean

    /**
     * Executed when the simulation ended, for example to log a result message.
     */
    abstract fun done()
}
