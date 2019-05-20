package com.avolosko.spacex.api

import com.avolosko.spacex.api.endpoints.LaunchesEndpoint
import com.avolosko.spacex.api.mapper.LaunchMapper
import com.avolosko.spacex.data.LaunchesDataSource
import com.avolosko.spacex.db.entity.LaunchEntity
import java.net.HttpURLConnection

class LaunchesApi(
    private val endpoint: LaunchesEndpoint,
    private val mapper: LaunchMapper
) : LaunchesDataSource {

    override fun getLaunches(): List<LaunchEntity>? {
        val response = endpoint.getAllLaunches().execute()

        if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
            return mapper.map(response.body()!!)
        }

        return null
    }

    override fun saveLaunches(launches: List<LaunchEntity>) {
        throw Exception("Illegal method call, it's cloud data source")
    }
}