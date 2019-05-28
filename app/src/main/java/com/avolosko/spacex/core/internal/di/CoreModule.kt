package com.avolosko.spacex.core.internal.di

import android.app.Application
import com.avolosko.spacex.core.UserSettings
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {

    @Provides
    @Singleton
    fun providesLaunchesDataSource(application: Application): UserSettings {
        return UserSettings(application)
    }
}