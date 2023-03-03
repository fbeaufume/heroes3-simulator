package com.adeliosys.heroes3simulator

fun main(args: Array<String>) {
    CombatSimulation(MinotaurKing(), 10, BattleDwarf(), 51, 1).run()
    CombatSimulation(MinotaurKing(), 10, BattleDwarf(), 52, 1).run()
    CombatSimulation(BattleDwarf(), 51, MinotaurKing(), 10, 1).run()

    CombatSimulation(SkeletonWarrior(), 10, Halberdier(), 7, 1).run()
    CombatSimulation(Halberdier(), 7, SkeletonWarrior(), 10, 1).run()

//    ValueSimulation(Archangel(), 1, Peasant(), 1).run()
//    ValueSimulation(MinotaurKing(), 10, BattleDwarf(), 1).run()
//    ValueSimulation(NagaQueen(), 10, Crusader(), 1).run()
//    ValueSimulation(AzureDragon(), 10, Archangel(), 1).run()
//    ValueSimulation(AzureDragon(), 20, Archangel(), 1).run()
}
