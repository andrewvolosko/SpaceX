package com.avolosko.spacex.ui.details

import android.support.v7.widget.RecyclerView
import android.view.View
import com.avolosko.spacex.ui.Launch
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_launch.*

class RocketLaunchViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(launch: Launch) {
        name.text = launch.name
        launchDate.text = launch.dateLabel
        successfull.text = launch.status.toString()
    }
}