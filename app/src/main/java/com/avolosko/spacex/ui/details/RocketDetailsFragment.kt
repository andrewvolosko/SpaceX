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
import com.avolosko.spacex.ui.Launch
import kotlinx.android.synthetic.main.fragment_rocket_details.*
import lecho.lib.hellocharts.model.LineChartData

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rocket_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = RocketDetailsPresenter(this, SpaceXApplication.rocketsService!!)
        adapter = RocketDetailsAdapter()

        detailsRV.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
        detailsRV.layoutManager = LinearLayoutManager(activity)
        detailsRV.adapter = adapter

        extractParams()
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
        val id = arguments!!.getString(ARG_ID)
        val description = arguments!!.getString(ARG_DESC)

        renderDescription(description!!)
        presenter!!.loadLaunches(id!!)
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