package com.adeliosys.heroes3simulator

import kotlin.math.min

/**
 * One stack of creatures.
 */
class CreatureStack(val creature: Creature, var initialQuantity: Int) {

    var quantity = initialQuantity

    /**
     * Reset the quantity to the initial quantity.
     */
    fun resetQuantity() {
        quantity = initialQuantity
        creature.resetCurrentHealth()
    }

    /**
     * Define the initial quantity and reset the quantity.
     */
    fun defineInitialQuantity(initialQuantity: Int) {
        this.initialQuantity = initialQuantity
        resetQuantity()
    }

    /**
     * This creature attacks another one.
     */
    fun attack(other: CreatureStack, round: Int, logLevel: Int) {
        // This creature attacks the other one
        var damage = computeDamage(other.creature)
        other.applyDamage(damage)

        log(logLevel, "Round $round: ${creature.name} attacks ${other.creature.name} for $damage damage: ${other.quantity} left (${other.creature.currentHealth} health)")

        // The other one retaliates, if possible
        if (other.isAlive() && !creature.hasAbility(Ability.NO_ENEMY_RETALIATION)) {
            damage = other.computeDamage(this.creature)
            applyDamage(damage)

            log(logLevel, "Round $round: ${other.creature.name} retaliates ${creature.name} for $damage damage: $quantity left (${creature.currentHealth} health)")
        }

        // Second attack, if possible
        if (other.isAlive() && creature.hasAbility(Ability.DOUBLE_ATTACK)) {
            damage = computeDamage(other.creature)
            other.applyDamage(damage)

            log(logLevel, "Round $round: ${creature.name} attacks ${other.creature.name} for $damage damage: ${other.quantity} left (${other.creature.currentHealth} health)")
        }
    }

    /**
     * Return true if this creature stack is alive.
     */
    fun isAlive() = quantity > 0

    /**
     * Damage formula details are available in [Damage formula](https://heroes.thelazy.net/index.php/Damage).
     */
    private fun computeDamage(other: Creature): Int {
        // Use the average creature damage instead of a random damage, to get a deterministic result
        val baseDamage = (creature.maxDamage + creature.minDamage) * 0.5 * quantity

        // Compute the attack and defense bonuses
        val attackDifference = creature.attack - other.defense
        val attackBonus = if (attackDifference > 0) min(attackDifference, 60) * 0.05 else 0.0
        val defenseBonus = if (attackDifference < 0) min(-attackDifference, 28) * 0.025 else 0.0

        val result = baseDamage * (1.0 + attackBonus) * (1.0 - defenseBonus)

        return result.toInt()
    }

    /**
     * Apply some damage to this creature stack.
     */
    private fun applyDamage(damage: Int) {
        // A stack health is the total health of all creatures of the stack
        val previousStackHealth = creature.initialHealth * (quantity - 1) + creature.currentHealth
        val newStackHealth = previousStackHealth - damage

        if (newStackHealth <= 0) {
            quantity = 0
            creature.currentHealth = 0
        } else if (newStackHealth % creature.initialHealth == 0) {
            quantity = newStackHealth / creature.initialHealth
            creature.currentHealth = creature.initialHealth
        } else {
            quantity = newStackHealth / creature.initialHealth + 1
            creature.currentHealth = newStackHealth % creature.initialHealth
        }
    }
}
