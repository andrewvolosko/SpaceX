package com.avolosko.spacex.ui.list

import com.avolosko.spacex.ui.Rocket

interface RocketListContract {
    interface View {
        fun renderRockets(items: List<Rocket>)

        fun renderError()

        fun showProgress()

        fun hideProgress()

        fun showWelcome()

        fun hideWelcome()
    }

    interface Presenter {
        fun loadAllRockets()

        fun showAll()

        fun showActive()

        fun start()

        fun stop()
    }
}