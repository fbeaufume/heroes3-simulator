package com.adeliosys.heroes3simulator

import kotlin.math.max
import kotlin.math.min

/**
 * One stack of creatures.
 */
class CreatureStack(val creature: Creature, var initialQuantity: Int) {

    var quantity = initialQuantity

    /**
     * Reset the quantity to the initial quantity.
     */
    fun reset() {
        quantity = initialQuantity
        creature.reset()
    }

    /**
     * Define the initial quantity and reset the quantity.
     */
    fun defineInitialQuantity(initialQuantity: Int) {
        this.initialQuantity = initialQuantity
        reset()
    }

    /**
     * This creature attacks another one.
     */
    @Suppress("NAME_SHADOWING")
    fun attack(other: CreatureStack, distance: Int, round: Int, logLevel: Int): Int {
        // Move if needed
        var distance = distance
        if (!creature.canShoot() && distance > 0) {
            distance = max (0, distance - creature.speed)

            log(logLevel, "Round $round: $quantity ${creature.name} advance, distance is now $distance")

            // If the attacker is still too far, do not attack
            if (distance > 0) {
                return distance
            }
        }

        // This creature stack attacks the other one
        var quantityBeforeAttack = other.quantity
        var damage = computeDamage(other.creature, distance)
        other.applyDamage(damage)
        creature.consumeAmmunition()

        log(logLevel, "Round $round: $quantity ${creature.name} attack $quantityBeforeAttack ${other.creature.name} for $damage damage: ${other.quantity} left (${other.creature.currentHealth} health)")

        // The other one retaliates, if possible
        if (other.isAlive() && !creature.hasAbility(Ability.NO_ENEMY_RETALIATION) && distance <= 0) {
            quantityBeforeAttack = quantity
            damage = other.computeDamage(this.creature, distance)
            applyDamage(damage)

            log(logLevel, "Round $round: ${other.quantity} ${other.creature.name} retaliate $quantityBeforeAttack ${creature.name} for $damage damage: $quantity left (${creature.currentHealth} health)")
        }

        // Second attack, if possible
        if (isAlive() && other.isAlive() && creature.hasAbility(Ability.DOUBLE_ATTACK)) {
            quantityBeforeAttack = other.quantity
            damage = computeDamage(other.creature, distance)
            other.applyDamage(damage)

            creature.consumeAmmunition()

            log(logLevel, "Round $round: $quantity ${creature.name} attack $quantityBeforeAttack ${other.creature.name} for $damage damage: ${other.quantity} left (${other.creature.currentHealth} health)")
        }

        return distance
    }

    /**
     * Return true if this creature stack is alive.
     */
    fun isAlive() = quantity > 0

    /**
     * Damage formula details are available in [Damage formula](https://heroes.thelazy.net/index.php/Damage).
     */
    private fun computeDamage(other: Creature, distance: Int): Int {
        // Use the average creature damage instead of a random damage, to get a deterministic result
        val baseDamage = (creature.maxDamage + creature.minDamage) * 0.5 * quantity

        // Compute the attack and defense bonuses
        val attackDifference = creature.attack - other.defense
        val attackBonus = if (attackDifference > 0) min(attackDifference, 60) * 0.05 else 0.0
        val defenseBonus = if (attackDifference < 0) min(-attackDifference, 28) * 0.025 else 0.0

        // Compute the range penalty, i.e. half damage when target is too far
        val rangePenalty = if (distance >= RANGED_PENALTY_DISTANCE && !creature.hasAbility(Ability.NO_DISTANCE_PENALTY)) 0.5 else 1.0

        // Compute the melee penalty, i.e. half damage when a shooter attacks a melee target
        val meleePenalty = if (distance <= 0 && creature.ranged && !creature.hasAbility(Ability.NO_MELEE_PENALTY)) 0.5 else 1.0

        val result = baseDamage * (1.0 + attackBonus) * (1.0 - defenseBonus) * rangePenalty * meleePenalty

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
