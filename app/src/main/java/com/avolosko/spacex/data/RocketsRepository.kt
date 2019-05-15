package com.avolosko.spacex.data

import com.avolosko.spacex.ui.Rocket

interface RocketsRepository {

    fun getRockets(callback: Callback)

    interface Callback{
        fun onSuccess(rockets:List<Rocket>)

        fun onError()
    }
}