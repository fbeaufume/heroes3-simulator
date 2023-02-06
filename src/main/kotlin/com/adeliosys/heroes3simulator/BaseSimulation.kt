package com.adeliosys.heroes3simulator

abstract class BaseSimulation(val stack1: CreatureStack, val stack2: CreatureStack, val logLevel: Int = 1) {

    /**
     * The current simulation step.
     */
    protected var step = 0;

    /**
     * Run all steps of the simulation.
     */
    fun run() {
        if (stack1 === stack2) {
            log(1, "Different creature stack instances must be used")
            return
        }

        if (stack1.creature === stack2.creature) {
            log(1, "Different creature instances must be used")
            return
        }

        while (true) {
            if (isOver()) break;

            step++

            if (runOneStep()) break;
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
