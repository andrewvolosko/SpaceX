package com.avolosko.spacex.api.internal.di

import com.avolosko.spacex.api.LaunchesApi
import com.avolosko.spacex.api.RocketsApi
import com.avolosko.spacex.data.LaunchesDataSource
import com.avolosko.spacex.data.RocketsDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


@Module
class ApiModule {


    @Provides
    @Singleton
    fun providesLaunchesDataSource(api: LaunchesApi): LaunchesDataSource {
        return api
    }

    @Named("cloud")
    @Provides
    @Singleton
    fun providesRocketsDataSource(api: RocketsApi): RocketsDataSource {
        return api
    }
}