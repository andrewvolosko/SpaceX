package com.avolosko.spacex.data

import com.avolosko.spacex.db.entity.LaunchEntity

interface LaunchesDataSource {

    fun getLaunches(): List<LaunchEntity>?

    fun saveLaunches(launches: List<LaunchEntity>)
}