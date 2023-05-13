package com.adeliosys.heroes3simulator

enum class Ability {

    /**
     * Attack twice.
     */
    DOUBLE_ATTACK,

    /**
     * Prevent enemy retaliation.
     */
    NO_ENEMY_RETALIATION,

    /**
     * No melee penalty for a ranged creature.
     */
    NO_MELEE_PENALTY,

    /**
     * No distance penalty for a ranged creature.
     */
    NO_DISTANCE_PENALTY,

    /**
     * Drain life when attacking.
     * TODO FBE this must not work on undead, elementals and golems
     */
    LIFE_DRAIN,

    /**
     * Reduce the enemy defense by 40%
     */
    REDUCE_DEFENSE_40,

    /**
     * Reduce the enemy defense by 90%
     */
    REDUCE_DEFENSE_80,

    /**
     * Reduce the enemy attack by 30%
     */
    REDUCE_ATTACK_30,

    /**
     * Reduce the enemy attack by 60%
     */
    REDUCE_ATTACK_60,

    /**
     * An undead creature.
     */
    UNDEAD,
}
