package com.avolosko.spacex.data

import com.avolosko.spacex.db.entity.LaunchEntity

interface LaunchesRepository {

    fun getLaunches(force: Boolean, callback: Callback)

    interface Callback {
        fun onSuccess(launches: List<LaunchEntity>)

        fun onError()
    }
}