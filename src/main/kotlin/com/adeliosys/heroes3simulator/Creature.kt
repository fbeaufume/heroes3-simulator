package com.adeliosys.heroes3simulator

/**
 * A creature descriptor.
 */
open class Creature(
    val name: String,
    val attack: Int,
    val defense: Int,
    val minDamage: Int,
    val maxDamage: Int,
    val initialHealth: Int,
    val speed: Int,
    val size: Int,
    val ranged: Boolean,
    private val initialAmmunition: Int,
    private vararg val abilities: Ability
) {
    var currentHealth = initialHealth

    private var currentAmmunition = initialAmmunition

    /**
     * Reset the variable properties of the creature.
     */
    fun reset() {
        currentHealth = initialHealth
        currentAmmunition = initialAmmunition
    }

    /**
     * Return true is the creature can shoot.
     */
    fun canShoot(): Boolean = ranged && currentAmmunition > 0

    fun consumeAmmunition() {
        currentAmmunition--
    }

    /**
     * Return true if this creature has a given ability.
     */
    fun hasAbility(ability: Ability): Boolean = abilities.contains(ability)
}

// See https://heroes.thelazy.net/index.php/List_of_creatures for creature stats

class Peasant() : Creature("Peasant", 1, 1, 1, 1, 1, 3, 1, false, 0)

class SkeletonWarrior() : Creature("Skeleton Warrior", 6, 6, 1, 3, 6, 5, 1, false, 0)

class Halberdier() : Creature("Halberdier", 6, 5, 2, 3, 10, 5, 1, false, 0)

class Marksman() : Creature("Marksman", 6, 3, 2, 3, 10, 6, 1, true, 24, Ability.DOUBLE_ATTACK)

class LizardWarrior() : Creature("Lizard Warrior", 6, 8, 2, 5, 15, 5, 1, true, 24)

class BattleDwarf() : Creature("Battle Dwarf", 7, 7, 2, 4, 20, 5, 1, false, 0)

class SilverPegasus() : Creature("Silver Pegasus", 9, 10, 5, 9, 30, 12, 2, false, 0)

class Crusader() : Creature("Crusader", 12, 12, 7, 10, 35, 6, 1, false, 0, Ability.DOUBLE_ATTACK)

class ArchMage() : Creature("Arch Mage", 12, 9, 7, 9, 30, 7, 1, true, 24, Ability.NO_MELEE_PENALTY)

class Zealot() : Creature("Zealot", 12, 10, 10, 12, 30, 7, 1, true, 24, Ability.NO_MELEE_PENALTY)

class MinotaurKing() : Creature("Minotaur King", 15, 15, 12, 20, 50, 8, 1, false, 0)

class DendroidSoldier() : Creature("Dendroid Soldier", 9, 12, 10, 14, 65, 4, 1, false, 0)

class NagaQueen() : Creature("Naga Queen", 16, 13, 30, 30, 110, 7, 2, false, 0, Ability.NO_ENEMY_RETALIATION)

class ArchDevil() : Creature("Arch Devil", 26, 28, 30, 40, 200, 17, 1, false, 0, Ability.NO_ENEMY_RETALIATION)

class Archangel() : Creature("Anchangel", 30, 30, 50, 50, 250, 18, 2, false, 0)

class AzureDragon() : Creature("Azure Dragon", 50, 50, 70, 80, 1000, 19, 2, false, 0)
