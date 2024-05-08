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
    val initialAmmunition: Int, // 0 means melee creature
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
    fun canShoot(): Boolean = currentAmmunition > 0

    fun consumeAmmunition(distance: Int) {
        if (distance > 0) {
            currentAmmunition--
        }
    }

    /**
     * Return true if this creature has a given ability.
     */
    fun hasAbility(ability: Ability): Boolean = abilities.contains(ability)

    /**
     * Return true if the creature is immune to drain life.
     */
    fun isImmuneToDrainLife(): Boolean =
        hasAbility(Ability.UNDEAD) || hasAbility(Ability.ELEMENTAL) || hasAbility(Ability.NON_LIVING)
}

// See https://heroes.thelazy.net/index.php/List_of_creatures for creature stats

// Level 1 creatures

class Peasant : Creature("Peasant", 1, 1, 1, 1, 1, 3, 1, 0)

class SkeletonWarrior : Creature(
    "Skeleton Warrior", 6, 6, 1, 3, 6, 5, 1, 0,
    Ability.UNDEAD
)

class Halberdier : Creature("Halberdier", 6, 5, 2, 3, 10, 5, 1, 0)

// Level 2 creatures

class Marksman : Creature(
    "Marksman", 6, 3, 2, 3, 10, 6, 1, 24,
    Ability.DOUBLE_ATTACK
)

class LizardWarrior : Creature("Lizard Warrior", 6, 8, 2, 5, 15, 5, 1, 24)

class BattleDwarf : Creature("Battle Dwarf", 7, 7, 2, 4, 20, 5, 1, 0)

// Level 4 creatures

class SilverPegasus : Creature("Silver Pegasus", 9, 10, 5, 9, 30, 12, 2, 0)

class FireElemental : Creature(
    "Fire Elemental", 10, 8, 4, 6, 35, 6, 1, 0,
    Ability.ELEMENTAL
)

class EnergyElemental : Creature(
    "Energy Elemental", 12, 8, 4, 6, 35, 8, 1, 0,
    Ability.ELEMENTAL
)

class Crusader : Creature(
    "Crusader", 12, 12, 7, 10, 35, 6, 1, 0,
    Ability.DOUBLE_ATTACK
)

class Vampire : Creature(
    "Vampire", 10, 9, 5, 8, 30, 6, 1, 0,
    Ability.NO_ENEMY_RETALIATION, Ability.UNDEAD
)

class VampireLord : Creature(
    "Vampire Lord", 10, 10, 5, 8, 40, 9, 1, 0,
    Ability.NO_ENEMY_RETALIATION, Ability.LIFE_DRAIN, Ability.UNDEAD
)

class ArchMage : Creature(
    "Arch Mage", 12, 9, 7, 9, 30, 7, 1, 24,
    Ability.NO_MELEE_PENALTY
)

class Sharpshooter : Creature(
    "Sharpshooter", 12, 10, 8, 10, 15, 9, 1, 32,
    Ability.NO_DISTANCE_PENALTY
)

// Level 5 creatures

class GoldGolem : Creature(
    "Gold Golem", 11, 12, 8, 10, 50, 5, 1, 0,
    Ability.NON_LIVING
)

class Zealot : Creature(
    "Zealot", 12, 10, 10, 12, 30, 7, 1, 24,
    Ability.NO_MELEE_PENALTY
)

class MinotaurKing : Creature("Minotaur King", 15, 15, 12, 20, 50, 8, 1, 0)

class DendroidSoldier : Creature("Dendroid Soldier", 9, 12, 10, 14, 65, 4, 1, 0)

// Level 6 creatures

class DiamondGolem : Creature(
    "Diamond Golem", 13, 12, 10, 14, 60, 5, 1, 0,
    Ability.NON_LIVING
)

class Manticore() : Creature("Manticore", 15, 13, 14, 20, 80, 7, 2, 0)

class Nix : Creature(
    "Nix", 13, 16, 18, 22, 80, 6, 1, 0,
    Ability.REDUCE_ATTACK_30
)

class NixWarrior : Creature(
    "Nix Warrior", 14, 17, 18, 22, 90, 7, 1, 0,
    Ability.REDUCE_ATTACK_60
)

class NagaQueen : Creature(
    "Naga Queen", 16, 13, 30, 30, 110, 7, 2, 0,
    Ability.NO_ENEMY_RETALIATION
)

// Level 7 creatures

class Behemot : Creature(
    "Behemot", 17, 17, 30, 50, 160, 6, 2, 0,
    Ability.REDUCE_DEFENSE_40
)

class AncientBehemot : Creature(
    "Ancient Behemot", 19, 19, 30, 50, 300, 9, 2, 0,
    Ability.REDUCE_DEFENSE_80
)

class ArchDevil : Creature(
    "Arch Devil", 26, 28, 30, 40, 200, 17, 1, 0,
    Ability.NO_ENEMY_RETALIATION
)

class Archangel() : Creature("Archangel", 30, 30, 50, 50, 250, 18, 2, 0)

class AzureDragon() : Creature("Azure Dragon", 50, 50, 70, 80, 1000, 19, 2, 0)
