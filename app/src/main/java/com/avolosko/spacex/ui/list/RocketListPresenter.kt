package com.avolosko.spacex.ui.list

import android.content.Context
import android.os.Handler
import com.avolosko.spacex.Const.Companion.WELCOME_TIME
import com.avolosko.spacex.core.UserSettings
import com.avolosko.spacex.api.RocketsService
import com.avolosko.spacex.api.Utils.Companion.callback
import com.avolosko.spacex.api.mapper.RocketMapper
import com.avolosko.spacex.ui.Rocket

class RocketListPresenter(
    context: Context,
    private var view: RocketListContract.View?,
    private val rocketsService: RocketsService
) :
    RocketListContract.Presenter {

    private var rockets: List<Rocket>? = null

    //TODO use dagger
    private val userSettings = UserSettings(context)

    override fun start() {
        showWelcome()
        loadAllRockets()
    }

    private fun showWelcome() {
        if (userSettings.isFirstLaunch) {
            showWelcome()
            Handler().postDelayed({ view?.hideWelcome() }, WELCOME_TIME)

            userSettings.isFirstLaunch = false
        }
    }

    override fun stop() {
        view = null
    }

    override fun loadAllRockets() {
        view?.showProgress()

        rocketsService.getAllRockets().enqueue(
            callback(
                { response ->
                    if (response.isSuccessful && response.body() != null) {
                        rockets = RocketMapper().map(response.body()!!)
                        view?.renderRockets(rockets!!)
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

    override fun showAll() {
        view?.renderRockets(rockets!!)
    }

    override fun showActive() {
        view?.renderRockets(rockets!!.filter {
            it.active
        })
    }
}