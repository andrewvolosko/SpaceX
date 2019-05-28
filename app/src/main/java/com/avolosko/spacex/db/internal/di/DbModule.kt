package com.avolosko.spacex.db.internal.di

import android.app.Application
import com.avolosko.spacex.data.LaunchesDataSource
import com.avolosko.spacex.data.RocketsDataSource
import com.avolosko.spacex.db.AppDatabase
import com.avolosko.spacex.db.LaunchesLocalDataSource
import com.avolosko.spacex.db.RocketsLocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class DbModule {

    @Named("local")
    @Provides
    @Singleton
    fun providesLaunchesDataSource(application: Application): LaunchesDataSource {
        return LaunchesLocalDataSource(AppDatabase.getInstance(application).launchesDao())
    }

    @Named("local")
    @Provides
    @Singleton
    fun providesRocketsDataSource(application: Application): RocketsDataSource {
        return RocketsLocalDataSource(AppDatabase.getInstance(application).rocketsDao())
    }
}