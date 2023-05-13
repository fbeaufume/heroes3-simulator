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
            distance = max(0, distance - creature.speed)

            log(logLevel, "Round $round: $quantity ${creature.name} advance, distance is now $distance")

            // If the attacker is still too far, do not attack
            if (distance > 0) {
                return distance
            }
        }

        // This creature stack attacks the other one
        var attackerQuantityBeforeAttack = quantity
        var defenderQuantityBeforeAttack = other.quantity
        var damage = computeDamage(other.creature, distance)
        other.applyDamage(damage, this)
        creature.consumeAmmunition()

        log(logLevel, "Round $round: $attackerQuantityBeforeAttack ${creature.name} attack $defenderQuantityBeforeAttack ${other.creature.name} for $damage damage: ${other.quantity} left (${other.creature.currentHealth} health)")

        // The other one retaliates, if possible
        if (other.isAlive() && !creature.hasAbility(Ability.NO_ENEMY_RETALIATION) && distance <= 0) {
            attackerQuantityBeforeAttack = other.quantity
            defenderQuantityBeforeAttack = quantity
            damage = other.computeDamage(this.creature, distance)
            applyDamage(damage, other)

            log(logLevel, "Round $round: $attackerQuantityBeforeAttack ${other.creature.name} retaliate $defenderQuantityBeforeAttack ${creature.name} for $damage damage: $quantity left (${creature.currentHealth} health)")
        }

        // Second attack, if possible
        if (isAlive() && other.isAlive() && creature.hasAbility(Ability.DOUBLE_ATTACK)) {
            attackerQuantityBeforeAttack = quantity
            defenderQuantityBeforeAttack = other.quantity
            damage = computeDamage(other.creature, distance)
            other.applyDamage(damage, this)
            creature.consumeAmmunition()

            log(logLevel, "Round $round: $attackerQuantityBeforeAttack ${creature.name} attack $defenderQuantityBeforeAttack ${other.creature.name} for $damage damage: ${other.quantity} left (${other.creature.currentHealth} health)")
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

        // Compute the effective attack, i.e. use the attack reduction ability
        val effectiveAttack = creature.attack * when {
            other.hasAbility(Ability.REDUCE_ATTACK_30) -> 0.7
            other.hasAbility(Ability.REDUCE_ATTACK_60) -> 0.4
            else -> 1.0
        }

        // Compute the effective defense, i.e. use the defense reduction ability
        val effectiveDefense = other.defense * when {
            creature.hasAbility(Ability.REDUCE_DEFENSE_40) -> 0.6
            creature.hasAbility(Ability.REDUCE_DEFENSE_80) -> 0.2
            else -> 1.0
        }

        // Compute the attack and defense bonuses
        val attackDifference = effectiveAttack - effectiveDefense
        val attackBonus = if (attackDifference > 0) min(attackDifference, 60.0) * 0.05 else 0.0
        val defenseBonus = if (attackDifference < 0) min(-attackDifference, 28.0) * 0.025 else 0.0

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
    private fun applyDamage(damage: Int, attacker: CreatureStack) {
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

        // Apply life drain if needed
        if (attacker.creature.hasAbility(Ability.LIFE_DRAIN) && !this.creature.isImmuneToDrainLife()) {
            // In multi stack fights, we should probably cap the drained amount by the actual health of the attacked stack
            attacker.addHealth(damage)
        }
    }

    /**
     * Add some health to the creature stack (for example as a consequence of the drain life ability),
     * possibly adding more creatures to the stack (up to the initial quantity).
     */
    private fun addHealth(healthAmount: Int) {
//        println("Before drain life of $healthAmount: quantity=$quantity, currentHealth=${creature.currentHealth}")

        val newHealthTotal = (quantity - 1) * creature.initialHealth + creature.currentHealth + healthAmount

        if (newHealthTotal / creature.initialHealth >= initialQuantity) {
            quantity = initialQuantity
            creature.currentHealth = creature.initialHealth
        } else {
            quantity = newHealthTotal / creature.initialHealth + 1
            creature.currentHealth = newHealthTotal % creature.initialHealth
        }

//        println("After drain life of $healthAmount: quantity=$quantity, currentHealth=${creature.currentHealth}")
    }
}
