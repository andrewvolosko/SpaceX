package com.avolosko.spacex.api

import com.avolosko.spacex.api.endpoints.RocketsEndpoint
import com.avolosko.spacex.api.mapper.RocketMapper
import com.avolosko.spacex.data.RocketsDataSource
import com.avolosko.spacex.ui.Rocket
import java.net.HttpURLConnection

class RocketsApi(
    private val endpoint: RocketsEndpoint,
    private val mapper: RocketMapper
) : RocketsDataSource {

    override fun getRockets(): List<Rocket>? {
        val response = endpoint.getAllRockets().execute()

        if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
            return mapper.map(response.body()!!)
        }
        return null
    }
}