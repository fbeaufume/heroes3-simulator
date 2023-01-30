package com.adeliosys.heroes3simulator

/**
 * A creature descriptor.
 */
class Creature(
    val name: String,
    val attack: Int,
    val defense: Int,
    val minDamage: Int,
    val maxDamage: Int,
    val initialHealth: Int
) {
    var currentHealth = initialHealth

    private val abilities: MutableList<Ability> = mutableListOf()

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
