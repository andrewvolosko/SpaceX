package com.avolosko.spacex.db

import com.avolosko.spacex.data.LaunchesDataSource
import com.avolosko.spacex.db.dao.LaunchesDao
import com.avolosko.spacex.db.entity.LaunchEntity

class LaunchesLocalDataSource(val dao: LaunchesDao) : LaunchesDataSource {
    override fun saveLaunches(launches: List<LaunchEntity>) {
        dao.saveLaunches(launches)
    }

    override fun getLaunches(): List<LaunchEntity>? {
        return dao.getAllLaunches()
    }
}