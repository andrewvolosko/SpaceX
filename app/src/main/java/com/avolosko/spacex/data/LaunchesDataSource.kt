package com.avolosko.spacex.data

import com.avolosko.spacex.ui.Launch

interface LaunchesDataSource {

    fun getLaunches(): List<Launch>?

}