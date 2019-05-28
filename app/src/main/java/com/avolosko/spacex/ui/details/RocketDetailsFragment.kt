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
import com.avolosko.spacex.R
import com.avolosko.spacex.SpaceXApplication
import com.avolosko.spacex.db.entity.LaunchEntity
import com.avolosko.spacex.ui.internal.di.DaggerViewComponent
import com.avolosko.spacex.ui.internal.di.PresentationModule
import com.avolosko.spacex.ui.internal.di.ViewModule
import kotlinx.android.synthetic.main.fragment_rocket_details.*
import lecho.lib.hellocharts.model.LineChartData
import javax.inject.Inject

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
    private lateinit var rocketId: String

    @Inject
    lateinit var presenter: RocketDetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onInitializeInjection()
    }

    override fun onInitializeInjection() {
        val app = activity!!.application as SpaceXApplication

        DaggerViewComponent.builder()
            .applicationComponent(app.getApplicationComponent())
            .viewModule(ViewModule(this))
            .presentationModule(PresentationModule())
            .build()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rocket_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RocketDetailsAdapter()

        detailsRV.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
        detailsRV.layoutManager = LinearLayoutManager(activity)
        detailsRV.adapter = adapter

        renderDescription(getRocketDescription())
        progress.setOnRefreshListener {
            presenter.loadLaunches(true, rocketId)
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
        presenter.loadLaunches(false, getRocketId())
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    private fun getRocketId(): String {
        return arguments!!.getString(ARG_ID)!!
    }

    private fun getRocketDescription(): String {
        return arguments!!.getString(ARG_DESC)!!
    }

    private fun renderDescription(label: String) {
        description.text = label
    }

    override fun renderGraph(chartData: LineChartData) {
        chart.lineChartData = chartData
    }

    override fun renderLaunches(launches: List<LaunchEntity>) {
        adapter.setLaunches(launches)
        adapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        progress.isRefreshing = true
    }

    override fun hideProgress() {
        progress.isRefreshing = false
    }

    override fun renderError() {
        Snackbar.make(rootView, R.string.failed_load_launches, Snackbar.LENGTH_SHORT)
    }
}