package com.avolosko.spacex.internal.di

import com.avolosko.spacex.api.endpoints.internal.di.EndpointsModule
import com.avolosko.spacex.api.internal.di.ApiModule
import com.avolosko.spacex.core.UserSettings
import com.avolosko.spacex.core.internal.di.CoreModule
import com.avolosko.spacex.data.LaunchesRepository
import com.avolosko.spacex.data.RocketsRepository
import com.avolosko.spacex.data.internal.di.DataModule
import com.avolosko.spacex.db.internal.di.DbModule
import com.avolosko.spacex.util.AppExecutors
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        CoreModule::class,
        ApiModule::class,
        DataModule::class,
        DbModule::class,
        EndpointsModule::class])
interface ApplicationComponent {

    fun getUserSettings(): UserSettings

    fun getThreadExecutor(): AppExecutors

    fun getRocketsRepository(): RocketsRepository

    fun getLaunchesRepository(): LaunchesRepository
}