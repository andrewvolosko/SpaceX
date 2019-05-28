package com.avolosko.spacex

import android.app.Application
import com.avolosko.spacex.core.internal.di.CoreModule
import com.avolosko.spacex.internal.di.ApplicationComponent
import com.avolosko.spacex.internal.di.ApplicationModule
import com.avolosko.spacex.internal.di.DaggerApplicationComponent

class SpaceXApplication : Application() {

    private lateinit var applicationComponent : ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .coreModule(CoreModule())
            .build()
    }

    fun getApplicationComponent(): ApplicationComponent {
        return applicationComponent
    }
}