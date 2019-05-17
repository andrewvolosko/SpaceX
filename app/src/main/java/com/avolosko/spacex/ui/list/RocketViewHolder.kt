package com.avolosko.spacex.ui.list

import android.support.v7.widget.RecyclerView
import android.view.View
import com.avolosko.spacex.R
import com.avolosko.spacex.db.entity.RocketEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_rocket.*

class RocketViewHolder(override val containerView: View, private val rocketClickListener: (Int) -> Unit) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(rocket: RocketEntity) {
        val resources = itemView.resources
        name.text = resources.getString(R.string.name, rocket.name)
        country.text = resources.getString(R.string.country, rocket.country)
        engines.text = resources.getString(R.string.engines, rocket.enginesCount)

        itemView.setOnClickListener { rocketClickListener.invoke(adapterPosition) }
    }
}