package com.avolosko.spacex.ui.details

import com.avolosko.spacex.api.RocketsService
import com.avolosko.spacex.api.Utils.Companion.callback
import com.avolosko.spacex.api.mapper.LaunchMapper
import com.avolosko.spacex.ui.Launch
import lecho.lib.hellocharts.model.*

class RocketDetailsPresenter(
    private var view: RocketDetailsContract.View?,
    private val rocketsService: RocketsService
) :
    RocketDetailsContract.Presenter {

    override fun start() {

    }

    override fun stop() {
        view = null
    }

    override fun loadLaunches(rocketId: String) {
        view?.showProgress()

        rocketsService.getAllLaunches().enqueue(
            callback(
                { response ->
                    if (response.isSuccessful && response.body() != null) {
                        val allLaunches = LaunchMapper().map(response.body()!!)
                        val graphData = prepareGraphData(allLaunches)
                        view?.renderGraph(graphData)

                        view?.renderLaunches(allLaunches.filter {
                            it.rocketId == rocketId
                        })
                    } else {
                        view?.renderError()
                    }
                    view?.hideProgress()
                },
                { th ->
                    view?.hideProgress()
                    view?.renderError()
                })
        )
    }

    private fun prepareGraphData(all: List<Launch>): LineChartData {
        val yearLaunches = mutableMapOf<Int, Int>()
        all.forEach {
            if (yearLaunches.contains(it.launchYear)) {
                val launches = yearLaunches[it.launchYear]
                yearLaunches[it.launchYear] = launches!! + 1
            } else {
                yearLaunches[it.launchYear] = 1
            }
        }

        val axisValues = yearLaunches.keys.mapIndexed { index, i ->
            AxisValue(index.toFloat()).setLabel(i.toString())
        }

        val yAxisValues = yearLaunches.map { it.value }.mapIndexed { index, i ->
            PointValue(index.toFloat(), i.toFloat())
        }

        val data = LineChartData()
        data.lines = listOf(Line(yAxisValues))
        data.axisXBottom = Axis(axisValues)
        return data
    }
}