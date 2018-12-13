package com.avolosko.spacex.ui

data class Rocket(
    val id: String,
    val name: String,
    val description: String,
    val country: String,
    val enginesCount: Int,
    val active: Boolean
)