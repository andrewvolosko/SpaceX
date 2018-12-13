package com.avolosko.spacex

import android.app.Application
import com.avolosko.spacex.Const.Companion.TIMEOUT
import com.avolosko.spacex.api.RocketsService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SpaceXApplication : Application() {

    //TODO use dagger
    companion object {
        var rocketsService: RocketsService? = null
    }

    override fun onCreate() {
        super.onCreate()
        initRocketService()
    }

    private fun initRocketService() {
        val client = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()

        val retrofitBase = Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        rocketsService = retrofitBase.create(RocketsService::class.java)
    }
}