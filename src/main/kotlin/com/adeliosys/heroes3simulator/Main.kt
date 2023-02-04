package com.adeliosys.heroes3simulator

fun main(args: Array<String>) {
//    CombatSimulation(CreatureStack(NagaQueen(), 10), CreatureStack(Crusader(), 43), 1).run()

    ValueSimulation(CreatureStack(AzureDragon(), 1), CreatureStack(Peasant(), 1), 2).run()
}
