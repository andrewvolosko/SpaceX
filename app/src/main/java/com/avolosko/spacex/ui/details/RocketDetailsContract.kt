package com.avolosko.spacex.ui.details

import com.avolosko.spacex.ui.Launch
import lecho.lib.hellocharts.model.LineChartData

interface RocketDetailsContract {

    interface View {
        fun renderGraph(chartData: LineChartData)

        fun renderLaunches(all: List<Launch>)

        fun renderError()

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter {
        fun loadLaunches(rocketId: String)

        fun start()

        fun stop()
    }
}