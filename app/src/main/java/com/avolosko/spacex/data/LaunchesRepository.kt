package com.avolosko.spacex.data

import com.avolosko.spacex.ui.Launch

interface LaunchesRepository {

    fun getLaunches(callback: Callback)

    interface Callback {
        fun onSuccess(launches: List<Launch>)

        fun onError()
    }
}