package com.avolosko.spacex.api

import com.avolosko.spacex.api.endpoints.RocketsEndpoint
import com.avolosko.spacex.api.mapper.RocketMapper
import com.avolosko.spacex.data.RocketsDataSource
import com.avolosko.spacex.db.entity.RocketEntity
import java.net.HttpURLConnection

class RocketsApi(
    private val endpoint: RocketsEndpoint,
    private val mapper: RocketMapper
) : RocketsDataSource {

    override fun getRockets(): List<RocketEntity>? {
        val response = endpoint.getAllRockets().execute()

        if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
            return mapper.map(response.body()!!)
        }
        return null
    }

    override fun saveRockets(rockets: List<RocketEntity>) {
        throw Exception("Illegal method call, it's cloud data source")
    }
}