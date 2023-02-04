package com.adeliosys.heroes3simulator

fun main(args: Array<String>) {
    println("Heroes 3 Simulator")

//    CombatSimulation(CreatureStack(NagaQueen(), 10), CreatureStack(Crusader(), 43), 1).run()

    ValueSimulation(CreatureStack(AzureDragon(), 1), CreatureStack(Peasant(), 1), 1).run()
}
