package com.avolosko.spacex.api.mapper

import com.avolosko.spacex.api.pojo.RocketResponse
import com.avolosko.spacex.db.entity.RocketEntity
import javax.inject.Inject

class RocketMapper @Inject constructor(){

    fun map(items: List<RocketResponse>): List<RocketEntity> {
        return items.map {
            RocketEntity(it.id, it.name, it.description, it.country, it.engines.number, it.active)
        }
    }
}