package com.adeliosys.heroes3simulator

fun main(args: Array<String>) {
//    CombatSimulation(MinotaurKing(), 10, BattleDwarf(), 51, 1).run()
//    CombatSimulation(MinotaurKing(), 10, BattleDwarf(), 52, 1).run()

//    CombatSimulation(SkeletonWarrior(), 10, Halberdier(), 7, 1).run()
//    CombatSimulation(Halberdier(), 7, SkeletonWarrior(), 10, 1).run()

//    CombatSimulation(LizardWarrior(), 10, DendroidSoldier(), 3, 2).run()
//    CombatSimulation(Sharpshooter(), 4, DendroidSoldier(), 3, 2).run()

    ValueSimulation(Marksman(), 1, Peasant(), 1).run()
    ValueSimulation(Marksman(), 10, Peasant(), 1).run()
    ValueSimulation(Marksman(), 100, Peasant(), 1).run()
    ValueSimulation(Sharpshooter(), 1, Peasant(), 1).run()
    ValueSimulation(Sharpshooter(), 10, Peasant(), 1).run()
    ValueSimulation(Sharpshooter(), 100, Peasant(), 1).run()
//    ValueSimulation(Crusader(), 1, Peasant(), 1).run()
//    ValueSimulation(Crusader(), 10, Peasant(), 1).run()
//    ValueSimulation(Crusader(), 100, Peasant(), 1).run()
//    ValueSimulation(Archangel(), 1, Peasant(), 1).run()
//    ValueSimulation(MinotaurKing(), 10, BattleDwarf(), 1).run()
//    ValueSimulation(NagaQueen(), 10, Crusader(), 1).run()
//    ValueSimulation(AzureDragon(), 10, Archangel(), 1).run()
//    ValueSimulation(AzureDragon(), 20, Archangel(), 1).run()
}
