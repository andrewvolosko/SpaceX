package com.avolosko.spacex.ui.internal.di

import com.avolosko.spacex.internal.di.ActivityScoped
import com.avolosko.spacex.internal.di.ApplicationComponent
import com.avolosko.spacex.ui.details.RocketDetailsFragment
import com.avolosko.spacex.ui.list.RocketsListFragment
import dagger.Component

@ActivityScoped
@Component(dependencies = [ApplicationComponent::class], modules = [ViewModule::class, PresentationModule::class ])
interface ViewComponent {

    fun inject(view: RocketsListFragment)

    fun inject(view: RocketDetailsFragment)
}