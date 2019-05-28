package com.avolosko.spacex.ui.details

import com.avolosko.spacex.db.entity.LaunchEntity
import com.avolosko.spacex.ui.AbsPresenter
import com.avolosko.spacex.ui.AbsView
import lecho.lib.hellocharts.model.LineChartData

interface RocketDetailsContract {

    interface View : AbsView{
        fun renderGraph(chartData: LineChartData)

        fun renderLaunches(launches: List<LaunchEntity>)

        fun renderError()
    }

    interface Presenter : AbsPresenter{
        fun loadLaunches(force: Boolean, rocketId: String)
    }
}