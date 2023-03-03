package com.adeliosys.heroes3simulator

fun main(args: Array<String>) {
//    CombatSimulation(MinotaurKing(), 10, BattleDwarf(), 51, 1).run() // Minotaur king win
//    CombatSimulation(MinotaurKing(), 10, BattleDwarf(), 52, 1).run() // Battle dwarf win
    CombatSimulation(BattleDwarf(), 51, MinotaurKing(), 10, 1).run() // Battle dwarf win, but it shouldn't be the case
//    ValueSimulation(Archangel(), 1, Peasant(), 1).run()
//    ValueSimulation(MinotaurKing(), 10, BattleDwarf(), 1).run()
//    ValueSimulation(NagaQueen(), 10, Crusader(), 1).run()
//    ValueSimulation(AzureDragon(), 10, Archangel(), 1).run()
//    ValueSimulation(AzureDragon(), 20, Archangel(), 1).run()
}
