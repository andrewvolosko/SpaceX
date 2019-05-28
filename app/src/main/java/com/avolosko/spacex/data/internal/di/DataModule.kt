package com.avolosko.spacex.data.internal.di

import com.avolosko.spacex.data.LaunchesRepository
import com.avolosko.spacex.data.LaunchesRepositoryImpl
import com.avolosko.spacex.data.RocketsRepository
import com.avolosko.spacex.data.RocketsRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun providesLaunchesRepository(repository: LaunchesRepositoryImpl): LaunchesRepository {
        return repository
    }

    @Provides
    @Singleton
    fun providesRocketsRepository(repository: RocketsRepositoryImpl): RocketsRepository {
        return repository
    }
}