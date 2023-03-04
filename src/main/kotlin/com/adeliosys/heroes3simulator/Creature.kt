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
    val ranged: Boolean,
    private vararg val abilities: Ability
) {
    var currentHealth = initialHealth

    /**
     * Reset the current health to the initial health.
     */
    fun resetCurrentHealth() {
        currentHealth = initialHealth
    }

    /**
     * Return true if this creature has a given ability.
     */
    fun hasAbility(ability: Ability): Boolean = abilities.contains(ability)
}

// See https://heroes.thelazy.net/index.php/List_of_creatures for creature stats

class Peasant() : Creature("Peasant", 1, 1, 1, 1, 1, 3, false)

class SkeletonWarrior() : Creature("Skeleton Warrior", 6, 6, 1, 3, 6, 5, false)

class Halberdier() : Creature("Halberdier", 6, 5, 2, 3, 10, 5, false)

class Marksman(): Creature("Marksman", 6, 3, 2, 3, 10, 6, true, Ability.DOUBLE_ATTACK)

class BattleDwarf() : Creature("Battle Dwarf", 7, 7, 2, 4, 20, 5, false)

class SilverPegasus() : Creature("Silver Pegasus", 9, 10, 5, 9, 30, 12, false)

class Crusader() : Creature("Crusader", 12, 12, 7, 10, 35,6, false, Ability.DOUBLE_ATTACK)

class ArchMage(): Creature("Arch Mage", 12, 9, 7, 9, 30, 7, true)

class Zealot(): Creature("Zealot", 12, 10, 10, 12, 30, 7, true)

class MinotaurKing() : Creature("Minotaur King", 15, 15, 12, 20, 50, 8, false)

class NagaQueen() : Creature("Naga Queen", 16, 13, 30, 30, 110, 7, false, Ability.NO_ENEMY_RETALIATION)

class ArchDevil() : Creature("Arch Devil", 26, 28, 30, 40, 200, 17, false, Ability.NO_ENEMY_RETALIATION)

class Archangel() : Creature("Anchangel", 30, 30, 50, 50, 250, 18, false)

class AzureDragon() : Creature("Azure Dragon", 50, 50, 70, 80, 1000, 19, false)
