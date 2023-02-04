package com.adeliosys.heroes3simulator

/**
 * Log a message if the level is strictly positive.
 */
fun log(level: Int, message: String) {
    if (level > 0) println(message)
}
