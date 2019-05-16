package com.avolosko.spacex.ui.details

import com.avolosko.spacex.data.LaunchesRepository
import com.avolosko.spacex.ui.AbsPresenter
import com.avolosko.spacex.ui.Launch
import lecho.lib.hellocharts.model.*

class RocketDetailsPresenter(
    private var view: RocketDetailsContract.View?,
    private val repository: LaunchesRepository
) :
    AbsPresenter(), RocketDetailsContract.Presenter {

    override fun start() {
        //no-op
    }

    override fun stop() {
        view = null
    }

    override fun loadLaunches(rocketId: String) {
        view?.showProgress()


        repository.getLaunches(object : LaunchesRepository.Callback{
            override fun onSuccess(launches: List<Launch>) {
                view?.hideProgress()

                val graphData = prepareGraphData(launches)
                view?.renderGraph(graphData)

                view?.renderLaunches(launches.filter {
                    it.rocketId == rocketId
                })
            }

            override fun onError() {
                view?.hideProgress()
                view?.renderError()
            }
        })
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