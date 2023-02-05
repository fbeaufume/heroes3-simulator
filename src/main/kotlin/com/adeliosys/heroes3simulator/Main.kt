package com.adeliosys.heroes3simulator

fun main(args: Array<String>) {
    CombatSimulation(NagaQueen(), 10, Crusader(), 40, 1).run()
    ValueSimulation(Archangel(), 10, Peasant(), 1).run()
    ValueSimulation(NagaQueen(), 10, Crusader(), 1).run()
    ValueSimulation(AzureDragon(), 10, Archangel(), 1).run()
    ValueSimulation(AzureDragon(), 20, Archangel(), 1).run()
}
