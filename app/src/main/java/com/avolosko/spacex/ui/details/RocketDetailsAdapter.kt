package com.avolosko.spacex.ui.details

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.avolosko.spacex.R
import com.avolosko.spacex.db.entity.LaunchEntity

class RocketDetailsAdapter : RecyclerView.Adapter<RocketLaunchViewHolder>() {

    private var allLaunches = emptyList<LaunchEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): RocketLaunchViewHolder {
        return RocketLaunchViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_launch, parent, false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: RocketLaunchViewHolder, pos: Int) {
        viewHolder.bind(allLaunches[pos])
    }

    fun setLaunches(launches: List<LaunchEntity>) {
        allLaunches = launches
    }

    override fun getItemCount(): Int {
        return allLaunches.size
    }
}