package com.avolosko.spacex.ui.list

import com.avolosko.spacex.db.entity.RocketEntity
import com.avolosko.spacex.ui.AbsPresenter
import com.avolosko.spacex.ui.AbsView

interface RocketsListContract {
    interface View : AbsView {
        fun renderRockets(items: List<RocketEntity>)

        fun renderError()

        fun showWelcome()

        fun hideWelcome()
    }

    interface Presenter : AbsPresenter{
        fun loadAllRockets(force: Boolean, active: Boolean)

        fun showAll()

        fun showActive()
    }
}