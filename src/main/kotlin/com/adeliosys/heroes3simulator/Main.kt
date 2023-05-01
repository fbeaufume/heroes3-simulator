package com.adeliosys.heroes3simulator

fun main(args: Array<String>) {
//    ValueSimulation(Crusader(), 1, Peasant()).run()
//    ValueSimulation(Crusader(), 10, Peasant()).run()
//    ValueSimulation(Crusader(), 100, Peasant()).run()
    ValueSimulation(Crusader(), 100, Vampire()).run()
    ValueSimulation(Crusader(), 100, VampireLord()).run()
//    ValueSimulation(Archangel(), 1, Peasant()).run()
//    ValueSimulation(MinotaurKing(), 10, BattleDwarf()).run()
//    ValueSimulation(NagaQueen(), 10, Crusader()).run()
//    ValueSimulation(Archangel(), 10, Manticore()).run()
//    ValueSimulation(Archangel(), 10, NixWarrior()).run()
//    ValueSimulation(Archangel(), 10, NagaQueen()).run()
//    ValueSimulation(Behemot(), 10, Archangel()).run()
//    ValueSimulation(AncientBehemot(), 10, Archangel()).run()
//    ValueSimulation(AzureDragon(), 10, Archangel()).run()
//    ValueSimulation(AzureDragon(), 20, Archangel()).run()
//    ValueSimulation(AzureDragon(), 1, AncientBehemot()).run()

    ValueSimulation(Vampire(), 1, Peasant()).run()
    ValueSimulation(Vampire(), 10, Peasant()).run()
    ValueSimulation(Vampire(), 100, Peasant()).run()

//    CombatSimulation(MinotaurKing(), 10, BattleDwarf(), 51).run()
//    CombatSimulation(MinotaurKing(), 10, BattleDwarf(), 52).run()
//    CombatSimulation(LizardWarrior(), 10, DendroidSoldier(), 3).run()
//    CombatSimulation(Sharpshooter(), 4, DendroidSoldier(), 3).run()
}
