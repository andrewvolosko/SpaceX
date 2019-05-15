package com.avolosko.spacex.ui.list

import android.content.Context
import android.os.Handler
import com.avolosko.spacex.WELCOME_TIME
import com.avolosko.spacex.core.UserSettings
import com.avolosko.spacex.data.RocketsRepository
import com.avolosko.spacex.ui.AbsPresenter
import com.avolosko.spacex.ui.Rocket

class RocketListPresenter(
    context: Context,
    private var view: RocketListContract.View?,
    private val repository: RocketsRepository
) :
    AbsPresenter(), RocketListContract.Presenter {

    private var rockets1: List<Rocket> = emptyList()

    //TODO use dagger
    private val userSettings = UserSettings(context)

    override fun start() {
        showWelcome()
    }

    private fun showWelcome() {
        if (userSettings.isFirstLaunch) {
            view?.showWelcome()
            Handler().postDelayed({ view?.hideWelcome() }, WELCOME_TIME)

            userSettings.isFirstLaunch = false
        }
    }

    override fun stop() {
        view = null
    }

    override fun loadAllRockets(active: Boolean) {
        view?.showProgress()

        repository.getRockets(object : RocketsRepository.Callback {
            override fun onSuccess(rockets: List<Rocket>) {
                rockets1 = rockets
                view?.hideProgress()

                if (active) {
                    view?.renderRockets(rockets.filter { it.active })
                } else {
                    view?.renderRockets(rockets)
                }
            }

            override fun onError() {
                view?.hideProgress()
                view?.renderError()
            }
        })
    }

    override fun showAll() {
        view?.renderRockets(rockets1)
    }

    override fun showActive() {
        if (rockets1.isNotEmpty()) {
            view?.renderRockets(rockets1.filter { it.active })
        }
    }
}