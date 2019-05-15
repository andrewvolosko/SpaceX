package com.avolosko.spacex.api.endpoints

import com.avolosko.spacex.api.pojo.LaunchResponse
import retrofit2.Call
import retrofit2.http.GET

interface LaunchesEndpoint {

    @GET("launches")
    fun getAllLaunches(): Call<List<LaunchResponse>>

}