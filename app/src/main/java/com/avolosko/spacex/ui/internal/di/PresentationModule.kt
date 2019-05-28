package com.avolosko.spacex.ui.internal.di

import com.avolosko.spacex.ui.details.RocketDetailsContract
import com.avolosko.spacex.ui.details.RocketDetailsPresenter
import com.avolosko.spacex.ui.list.RocketsListContract
import com.avolosko.spacex.ui.list.RocketsListPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun providesRocketsListPresenter(presenter: RocketsListPresenter): RocketsListContract.Presenter {
        return presenter
    }

    @Provides
    fun providesRocketDetailsPresenter(presenter: RocketDetailsPresenter): RocketDetailsContract.Presenter {
        return presenter
    }
}