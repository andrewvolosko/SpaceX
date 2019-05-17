package com.avolosko.spacex.data

import com.avolosko.spacex.db.entity.RocketEntity

interface RocketsDataSource {

    fun getRockets(): List<RocketEntity>?

    fun saveRockets(rockets: List<RocketEntity>)
}