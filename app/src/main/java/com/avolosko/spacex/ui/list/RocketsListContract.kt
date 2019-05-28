package com.avolosko.spacex.ui.list

import com.avolosko.spacex.db.entity.RocketEntity

interface RocketsListContract {
    interface View {
        fun renderRockets(items: List<RocketEntity>)

        fun renderError()

        fun showProgress()

        fun hideProgress()

        fun showWelcome()

        fun hideWelcome()
    }

    interface Presenter {
        fun loadAllRockets(force:Boolean, active: Boolean)

        fun showAll()

        fun showActive()

        fun start()

        fun stop()
    }
}