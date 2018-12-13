package com.avolosko.spacex.ui.list

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView.VERTICAL
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avolosko.spacex.R
import com.avolosko.spacex.SpaceXApplication
import com.avolosko.spacex.ui.Rocket
import com.avolosko.spacex.ui.details.RocketDetailsFragment
import kotlinx.android.synthetic.main.fragment_rocket.*

class RocketsListFragment : Fragment(), RocketListContract.View {

    private lateinit var adapter: RocketsAdapter
    private lateinit var alertDialog: AlertDialog

    //TODO use dagger
    private var presenter: RocketListContract.Presenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rocket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = RocketListPresenter(activity!!, this, SpaceXApplication.rocketsService!!)
        adapter = RocketsAdapter {
            showRocketDetails(it)
        }

        rocketsRV.addItemDecoration(DividerItemDecoration(activity, VERTICAL))
        rocketsRV.layoutManager = LinearLayoutManager(activity)
        rocketsRV.adapter = adapter
        filter.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                presenter!!.showActive()
            } else {
                presenter!!.showActive()
            }
        }

        progress.setOnRefreshListener {
            presenter!!.loadAllRockets()
        }
    }

    private fun showRocketDetails(rocket: Rocket) {
        val fragment = RocketDetailsFragment.getInstance(rocket.id, rocket.description)
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        val tag = fragment.javaClass.simpleName

        transaction.replace(R.id.container, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }

    override fun onStart() {
        super.onStart()
        presenter?.start()
    }

    override fun onStop() {
        super.onStop()
        presenter?.stop()
        hideWelcome()
    }

    override fun showWelcome() {
        alertDialog = AlertDialog.Builder(activity!!, R.style.DialogFullScreen)
            .setView(R.layout.dialog_welcome)
            .create()

        alertDialog.show()
    }

    override fun hideWelcome() {
        alertDialog.hide()
    }

    override fun showProgress() {
        progress.isRefreshing = true
    }

    override fun hideProgress() {
        progress.isRefreshing = false
    }

    override fun renderRockets(items: List<Rocket>) {
        adapter.setRockets(items)
        adapter.notifyDataSetChanged()
    }

    override fun renderError() {
        Snackbar.make(rootView, R.string.error_general, Snackbar.LENGTH_SHORT)
    }
}