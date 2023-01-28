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
}
