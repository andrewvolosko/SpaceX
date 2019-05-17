package com.avolosko.spacex.db

import com.avolosko.spacex.data.RocketsDataSource
import com.avolosko.spacex.db.dao.RocketsDao
import com.avolosko.spacex.db.entity.RocketEntity

class RocketsLocalDataSource(val dao: RocketsDao) : RocketsDataSource {

    override fun saveRockets(rockets: List<RocketEntity>) {
        dao.saveAllRockets(rockets)
    }

    override fun getRockets(): List<RocketEntity>? {
        return dao.getAllRockets()
    }
}