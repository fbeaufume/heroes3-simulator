package com.adeliosys.heroes3simulator

fun main(args: Array<String>) {
    println("Heroes 3 Simulator")

    // See https://heroes.thelazy.net/index.php/List_of_creatures for creature stats
    val peasants = CreatureStack(Creature("Peasant", 1, 1, 1, 1, 1), 441)
    val halberdiers = CreatureStack(Creature("Halberdier", 6, 5, 2, 3, 10), 1)
    val battleDwarfs = CreatureStack(Creature("Battle Dwarf", 7, 7, 2, 4, 20), 1)
    val silverPegasi = CreatureStack(Creature("Silver Pegasus", 9, 10, 5, 9, 30), 1)
    val minotaurKings = CreatureStack(Creature("Minotaur King", 15, 15, 12, 20, 50), 1)
    val nagaQueens = CreatureStack(Creature("Naga Queen", 16, 13, 30, 30, 110).addAbilities(Ability.NO_ENEMY_RETALIATION), 1)
    val archDevil = CreatureStack(Creature("Arch Devil", 26, 28, 30, 40, 200).addAbilities(Ability.NO_ENEMY_RETALIATION), 1)
    val archangels = CreatureStack(Creature("Anchangel", 30, 30, 50, 50, 250), 1)
    val azureDragon = CreatureStack(Creature("Azure Dragon", 50, 50, 70, 80, 1000), 1)

    CombatSimulation(archDevil, peasants).run()
}
