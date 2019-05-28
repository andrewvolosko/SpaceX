package com.avolosko.spacex.ui.internal.di

import android.support.v4.app.Fragment
import com.avolosko.spacex.ui.details.RocketDetailsContract
import com.avolosko.spacex.ui.list.RocketsListContract
import dagger.Module
import dagger.Provides

@Module
class ViewModule(private val frag: Fragment) {

    @Provides
    fun providesRocketsListView(): RocketsListContract.View {
        return frag as RocketsListContract.View
    }

    @Provides
    fun providesRocketDetailsView(): RocketDetailsContract.View {
        return frag as RocketDetailsContract.View
    }
}