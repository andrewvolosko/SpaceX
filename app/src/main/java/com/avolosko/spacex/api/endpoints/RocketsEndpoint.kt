package com.avolosko.spacex.api.endpoints

import com.avolosko.spacex.api.pojo.RocketResponse
import retrofit2.Call
import retrofit2.http.GET

interface RocketsEndpoint {

    @GET("rockets")
    fun getAllRockets(): Call<List<RocketResponse>>

}