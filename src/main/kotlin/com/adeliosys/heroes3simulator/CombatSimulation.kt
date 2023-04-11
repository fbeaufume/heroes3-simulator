package com.adeliosys.heroes3simulator

/**
 * Simulate a combat between two stacks of creatures.
 */
class CombatSimulation(creature1: Creature, initialQuantity1: Int, creature2: Creature, initialQuantity2: Int, logLevel: Int = 1) : BaseSimulation(logLevel) {

    /**
     * This distance is the number of hexes between the creature stacks.
     * Zero means that they can use melee attacks.
     */
    private var distance = 0

    /**
     * The creature on the left side of the fight.
     */
    private val leftCreature = creature1

    /**
     * The fastest of the two creature stacks.
     */
    private val stack1: CreatureStack

    /**
     * The slowest of the two creature stacks.
     */
    private val stack2: CreatureStack

    init {
        // Sort the stacks by decreasing creature speed
        val stacks = listOf(CreatureStack(creature1, initialQuantity1), CreatureStack(creature2, initialQuantity2)).sortedBy { it.creature.speed }
        stack1 = stacks[1]
        stack2 = stacks[0]

        checkQuantity(stack1)
        checkQuantity(stack2)
        checkCreatureStacks(stack1, stack2)

        distance = BATTLEFIELD_WIDTH - stack1.creature.size - stack2.creature.size
    }

    /**
     * The combat winner stack.
     */
    private var winner: CreatureStack = stack1

    override fun runOneStep(): Boolean {
        // When both stacks cannot shoot, do not make them move but make them at melee range
        // This is needed to be sure that the fastest creature strikes first, thanks to proper usage of the Wait command
        if (distance > 0 && !stack1.creature.canShoot() && !stack2.creature.canShoot()) {
            distance = 0
            log(logLevel - 1, "Creature stacks are now at melee range")
        }

        distance = stack1.attack(stack2, distance, step, logLevel - 1)

        if (isOver()) return true

        distance = stack2.attack(stack1, distance, step, logLevel - 1)

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
     * Return true if the left stack won the combat.
     */
    fun didLeftStackWin(): Boolean = winner.creature === leftCreature
}
