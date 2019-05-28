package com.avolosko.spacex.ui.details

import android.support.v7.widget.RecyclerView
import android.view.View
import com.avolosko.spacex.db.entity.LaunchEntity
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_launch.*

class RocketLaunchViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(launch: LaunchEntity) {
        name.text = launch.name
        launchDate.text = launch.dateLabel
        successfull.text = launch.status.toString()

        Glide.with(containerView)
            .load(launch.imageUrl)
            .into(image)
    }
}