package com.avolosko.spacex.data

import com.avolosko.spacex.ui.Rocket

interface RocketsDataSource {

    fun getRockets(): List<Rocket>?

}