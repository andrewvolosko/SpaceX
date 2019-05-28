package com.avolosko.spacex.internal.di

import android.app.Application
import com.avolosko.spacex.util.AppExecutors
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(): AppExecutors {
        return AppExecutors()
    }
}