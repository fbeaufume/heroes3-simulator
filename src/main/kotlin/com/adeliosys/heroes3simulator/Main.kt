package com.adeliosys.heroes3simulator

fun main(args: Array<String>) {
    println("Heroes 3 Simulator")

    // See https://heroes.thelazy.net/index.php/List_of_creatures for creature stats
    val archangels = CreatureStack(Creature("Anchangel", 30, 30, 50, 50, 250), 1)
    val halberdiers = CreatureStack(Creature("Halberdier", 6, 5, 2, 3, 10), 10)
    val battleDwarfs = CreatureStack(Creature("Battle Dwarf", 7, 7, 2, 4, 20), 5)
    val peasants = CreatureStack(Creature("Peasant", 1, 1, 1, 1, 1), 453)

    CombatSimulation(archangels, peasants).run()
}
