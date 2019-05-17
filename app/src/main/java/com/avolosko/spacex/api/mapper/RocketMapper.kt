package com.avolosko.spacex.api.mapper

import com.avolosko.spacex.api.pojo.RocketResponse
import com.avolosko.spacex.db.entity.RocketEntity

class RocketMapper {

    fun map(items: List<RocketResponse>): List<RocketEntity> {
        return items.map {
            RocketEntity(it.id, it.name, it.description, it.country, it.engines.number, it.active)
        }
    }
}