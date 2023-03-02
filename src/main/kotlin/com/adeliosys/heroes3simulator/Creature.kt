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
    abilities: List<Ability> = listOf()
) {
    var currentHealth = initialHealth

    private val abilities: MutableList<Ability> = mutableListOf()

    init {
        this.abilities.addAll(abilities)
    }

    /**
     * Reset the current health to the initial health.
     */
    fun resetCurrentHealth() {
        currentHealth = initialHealth
    }

    /**
     * Add abilities to this creature.
     */
    fun addAbilities(vararg abilities: Ability): Creature {
        this.abilities.addAll(abilities)
        return this
    }

    /**
     * Return true if this creature has a given ability.
     */
    fun hasAbility(ability: Ability): Boolean = abilities.contains(ability)
}

// See https://heroes.thelazy.net/index.php/List_of_creatures for creature stats

class Peasant() : Creature("Peasant", 1, 1, 1, 1, 1, 3)

class SkeletonWarrior() : Creature("Skeleton Warrior", 6, 6, 1, 3, 6, 5)

class Halberdier() : Creature("Halberdier", 6, 5, 2, 3, 10, 5)

class BattleDwarf() : Creature("Battle Dwarf", 7, 7, 2, 4, 20, 5)

class SilverPegasus() : Creature("Silver Pegasus", 9, 10, 5, 9, 30, 12)

class Crusader() : Creature("Crusader", 12, 12, 7, 10, 35,6, listOf(Ability.DOUBLE_ATTACK))

class MinotaurKing() : Creature("Minotaur King", 15, 15, 12, 20, 50, 8)

class NagaQueen() : Creature("Naga Queen", 16, 13, 30, 30, 110, 7, listOf(Ability.NO_ENEMY_RETALIATION))

class ArchDevil() : Creature("Arch Devil", 26, 28, 30, 40, 200, 17, listOf(Ability.NO_ENEMY_RETALIATION))

class Archangel() : Creature("Anchangel", 30, 30, 50, 50, 250, 18)

class AzureDragon() : Creature("Azure Dragon", 50, 50, 70, 80, 1000, 19)
