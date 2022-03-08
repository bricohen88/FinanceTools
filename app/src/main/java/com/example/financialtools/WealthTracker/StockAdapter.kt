package com.example.financialtools.WealthTracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.financialtools.R
import com.example.financialtools.database.StockEntity

class StockAdapter: ListAdapter<StockEntity,StockAdapter.StockViewHolder>(DiffCallback) {

    class StockViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val symbolText: TextView = view.findViewById(R.id.stock_symbol)
        val shortNameText: TextView = view.findViewById(R.id.stock_shortName)
        val priceText: TextView = view.findViewById(R.id.stock_price)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<StockEntity>() {
        override fun areItemsTheSame(oldItem: StockEntity, newItem: StockEntity): Boolean {
            return oldItem.shortName == newItem.symbol

        }

        override fun areContentsTheSame(oldItem: StockEntity, newItem: StockEntity): Boolean {
            return oldItem.price == newItem.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.stock_item,parent,false)
        return StockViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        holder.symbolText.text = getItem(position).symbol
        holder.shortNameText.text = getItem(position).shortName
        holder.priceText.text = getItem(position).price.toString()

    }
}