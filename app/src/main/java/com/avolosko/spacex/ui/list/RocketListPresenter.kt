package com.avolosko.spacex.ui.list

import android.content.Context
import android.os.Handler
import com.avolosko.spacex.WELCOME_TIME
import com.avolosko.spacex.core.UserSettings
import com.avolosko.spacex.data.RocketsRepository
import com.avolosko.spacex.db.entity.RocketEntity
import com.avolosko.spacex.ui.AbsPresenter

class RocketListPresenter(
    context: Context,
    private var view: RocketListContract.View?,
    private val repository: RocketsRepository
) : AbsPresenter(), RocketListContract.Presenter {

    private var localRockets: List<RocketEntity> = emptyList()

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

    override fun loadAllRockets(force: Boolean, active: Boolean) {
        view?.showProgress()

        repository.getRockets(force, object : RocketsRepository.Callback {
            override fun onSuccess(rockets: List<RocketEntity>) {
                localRockets = rockets
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
        view?.renderRockets(localRockets)
    }

    override fun showActive() {
        view?.renderRockets(localRockets.filter { it.active })
    }
}