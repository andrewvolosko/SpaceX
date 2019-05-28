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
import com.avolosko.spacex.BASE_URL
import com.avolosko.spacex.R
import com.avolosko.spacex.SpaceXApplication
import com.avolosko.spacex.TIMEOUT
import com.avolosko.spacex.api.RocketsApi
import com.avolosko.spacex.api.endpoints.RocketsEndpoint
import com.avolosko.spacex.api.mapper.RocketMapper
import com.avolosko.spacex.data.RocketsRepositoryImpl
import com.avolosko.spacex.db.AppDatabase
import com.avolosko.spacex.db.RocketsLocalDataSource
import com.avolosko.spacex.db.entity.RocketEntity
import com.avolosko.spacex.ui.details.RocketDetailsFragment
import com.avolosko.spacex.ui.internal.di.DaggerViewComponent
import com.avolosko.spacex.ui.internal.di.PresentationModule
import com.avolosko.spacex.ui.internal.di.ViewModule
import com.avolosko.spacex.util.AppExecutors
import kotlinx.android.synthetic.main.fragment_rocket.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class RocketsListFragment : Fragment(), RocketsListContract.View {

    private lateinit var adapter: RocketsAdapter
    private var alertDialog: AlertDialog? = null

    @Inject
    lateinit var presenter: RocketsListContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rocket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val app = activity!!.application as SpaceXApplication

        DaggerViewComponent.builder()
            .applicationComponent(app.getApplicationComponent())
            .viewModule(ViewModule(this))
            .presentationModule(PresentationModule())
            .build()
            .inject(this)

        val client = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()

        val retrofitBase = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

//        val dataSource = RocketsApi(retrofitBase.create(RocketsEndpoint::class.java), RocketMapper())
//        val localDataSource = RocketsLocalDataSource(AppDatabase.getInstance(activity!!).rocketsDao())
//        val repository = RocketsRepositoryImpl(AppExecutors(), dataSource, localDataSource)
//
//        presenter = RocketsListPresenter(activity!!, this, repository)
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
                presenter!!.showAll()
            }
        }

        progress.setOnRefreshListener {
            presenter!!.loadAllRockets(true, filter.isChecked)
        }
    }

    private fun showRocketDetails(rocket: RocketEntity) {
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
        presenter?.loadAllRockets(false, filter.isChecked)
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

        alertDialog!!.show()
    }

    override fun hideWelcome() {
        alertDialog?.hide()
    }

    override fun showProgress() {
        progress.isRefreshing = true
    }

    override fun hideProgress() {
        progress.isRefreshing = false
    }

    override fun renderRockets(items: List<RocketEntity>) {
        adapter.setRockets(items)
        adapter.notifyDataSetChanged()
    }

    override fun renderError() {
        Snackbar.make(rootView, R.string.error_general, Snackbar.LENGTH_SHORT)
    }
}