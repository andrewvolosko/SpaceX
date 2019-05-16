package com.avolosko.spacex.ui.details

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avolosko.spacex.BASE_URL
import com.avolosko.spacex.R
import com.avolosko.spacex.TIMEOUT
import com.avolosko.spacex.api.LaunchesApi
import com.avolosko.spacex.api.endpoints.LaunchesEndpoint
import com.avolosko.spacex.api.mapper.LaunchMapper
import com.avolosko.spacex.data.LaunchesRepositoryImpl
import com.avolosko.spacex.ui.Launch
import com.avolosko.spacex.util.AppExecutors
import kotlinx.android.synthetic.main.fragment_rocket_details.*
import lecho.lib.hellocharts.model.LineChartData
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RocketDetailsFragment : Fragment(), RocketDetailsContract.View {

    companion object {
        const val ARG_ID = "id"
        const val ARG_DESC = "description"

        fun getInstance(rocketId: String, description: String): RocketDetailsFragment {
            val args = Bundle()
            args.putString(ARG_ID, rocketId)
            args.putString(ARG_DESC, description)

            val frag = RocketDetailsFragment()
            frag.arguments = args
            return frag
        }
    }

    private lateinit var adapter: RocketDetailsAdapter

    //TODO use dagger
    private var presenter: RocketDetailsContract.Presenter? = null
    private lateinit var rocketId: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rocket_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val client = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()

        val retrofitBase = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val dataSource = LaunchesApi(retrofitBase.create(LaunchesEndpoint::class.java), LaunchMapper())
        val repository = LaunchesRepositoryImpl(AppExecutors(), dataSource)

        presenter = RocketDetailsPresenter(this, repository)
        adapter = RocketDetailsAdapter()

        detailsRV.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
        detailsRV.layoutManager = LinearLayoutManager(activity)
        detailsRV.adapter = adapter

        extractParams()

        progress.setOnRefreshListener {
            presenter!!.loadLaunches(rocketId)
        }
    }

    override fun onStart() {
        super.onStart()
        presenter!!.start()
    }

    override fun onStop() {
        super.onStop()
        presenter!!.stop()
    }

    private fun extractParams() {
        rocketId = arguments!!.getString(ARG_ID)!!
        val description = arguments!!.getString(ARG_DESC)

        renderDescription(description!!)
        presenter!!.loadLaunches(rocketId)
    }

    private fun renderDescription(label: String) {
        description.text = label
    }

    override fun renderGraph(launches: LineChartData) {
        chart.lineChartData = launches
    }

    override fun renderLaunches(all: List<Launch>) {
        adapter.setLaunches(all)
        adapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        progress.isRefreshing = true
    }

    override fun hideProgress() {
        progress.isRefreshing = false
    }

    override fun renderError() {
        Snackbar.make(rootView, R.string.error_general, Snackbar.LENGTH_SHORT)
    }
}