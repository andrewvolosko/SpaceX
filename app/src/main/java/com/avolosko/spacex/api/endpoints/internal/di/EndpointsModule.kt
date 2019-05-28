package com.avolosko.spacex.api.endpoints.internal.di

import com.avolosko.spacex.BASE_URL
import com.avolosko.spacex.api.endpoints.LaunchesEndpoint
import com.avolosko.spacex.api.endpoints.RocketsEndpoint
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class EndpointsModule {

    private val client = OkHttpClient.Builder()
        //.addInterceptor(HeaderInterceptor())
        //.addNetworkInterceptor(RequestLogInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideLaunchesEndpoint(): LaunchesEndpoint {
        return getRetrofitBuilder(BASE_URL).create(LaunchesEndpoint::class.java)
    }

    @Provides
    @Singleton
    fun provideRocketsEndpoint(): RocketsEndpoint {
        return getRetrofitBuilder(BASE_URL).create(RocketsEndpoint::class.java)
    }

    private fun getRetrofitBuilder(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}