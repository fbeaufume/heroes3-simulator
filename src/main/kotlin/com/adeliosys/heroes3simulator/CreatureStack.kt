package com.adeliosys.heroes3simulator

import kotlin.math.max
import kotlin.math.min

/**
 * One stack of creatures.
 */
class CreatureStack(val creature: Creature, val initialQuantity: Int) {

    var quantity = initialQuantity

    /**
     * Damage formula details are available in [Damage formula](https://heroes.thelazy.net/index.php/Damage).
     */
    fun computeDamage(other: Creature): Int {
        // Use the average creature damage instead of a random damage, to get a deterministic result
        val baseDamage = (other.maxDamage - other.minDamage) * 0.5 * quantity;

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
    fun applyDamage(damage: Int) {
        // The current total health of all creatures of the stack
        val currentStackHealth = creature.initialHealth * (quantity - 1) + creature.currentHealth
        val newStackHealth = max(currentStackHealth - damage, 0)

        quantity = newStackHealth / creature.initialHealth
        creature.currentHealth = newStackHealth % creature.initialHealth
    }
}
