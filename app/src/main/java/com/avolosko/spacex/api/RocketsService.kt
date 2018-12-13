package com.avolosko.spacex.api

import com.avolosko.spacex.api.pojo.LaunchResponse
import com.avolosko.spacex.api.pojo.RocketResponse
import retrofit2.Call
import retrofit2.http.GET

interface RocketsService {

    @GET("rockets")
    fun getAllRockets(): Call<List<RocketResponse>>

    @GET("launches")
    fun getAllLaunches(): Call<List<LaunchResponse>>
}