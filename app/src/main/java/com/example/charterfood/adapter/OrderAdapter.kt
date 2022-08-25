package com.example.charterfood.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.charterfood.R
import com.example.charterfood.data.Order

class OrderAdapter(
    private var listener: OnClickListener,
    private val orderList: List<Order>,
    private val onClickListener: (Order) -> Unit) : RecyclerView.Adapter<OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return OrderViewHolder(layoutInflater.inflate(R.layout.item_order, parent, false))
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = orderList[position]
        holder.render(item, onClickListener)
        holder.setListener(item, listener)
    }

    override fun getItemCount(): Int = orderList.size

}