package com.avolosko.spacex.data

import com.avolosko.spacex.db.entity.RocketEntity

interface RocketsRepository {

    fun getRockets(force: Boolean, callback: Callback)

    interface Callback {
        fun onSuccess(rockets: List<RocketEntity>)

        fun onError()
    }
}