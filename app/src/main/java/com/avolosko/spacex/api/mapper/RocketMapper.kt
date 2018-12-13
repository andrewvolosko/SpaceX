package com.avolosko.spacex.api.mapper

import com.avolosko.spacex.api.pojo.RocketResponse
import com.avolosko.spacex.ui.Rocket

class RocketMapper {

    fun map(items: List<RocketResponse>): List<Rocket> {
        return items.map {
            Rocket(it.id, it.name, it.description, it.country, it.engines.number, it.active)
        }
    }
}