package com.avolosko.spacex.ui.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.avolosko.spacex.R
import com.avolosko.spacex.db.entity.RocketEntity

class RocketsAdapter(private val rocketClickListener: (RocketEntity) -> Unit) : RecyclerView.Adapter<RocketViewHolder>() {

    private var items = emptyList<RocketEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): RocketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rocket, parent, false)
        return RocketViewHolder(view) { rocketClickListener.invoke(items[it]) }
    }

    override fun onBindViewHolder(viewHolder: RocketViewHolder, pos: Int) {
        viewHolder.bind(items[pos])
    }

    fun setRockets(items: List<RocketEntity>) {
        this.items = items
    }

    override fun getItemCount(): Int {
        return items.size
    }
}