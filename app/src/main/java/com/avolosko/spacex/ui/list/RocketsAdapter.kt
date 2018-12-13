package com.avolosko.spacex.ui.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.avolosko.spacex.R
import com.avolosko.spacex.ui.Rocket

class RocketsAdapter(private val rocketClickListener: (Rocket) -> Unit) : RecyclerView.Adapter<RocketViewHolder>() {

    private var items = emptyList<Rocket>()

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): RocketViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rocket, parent, false)
        return RocketViewHolder(view) { rocketClickListener.invoke(items[it]) }
    }

    override fun onBindViewHolder(viewHolder: RocketViewHolder, pos: Int) {
        viewHolder.bind(items[pos])
    }

    fun setRockets(items: List<Rocket>) {
        this.items = items
    }

    override fun getItemCount(): Int {
        return items.size
    }
}