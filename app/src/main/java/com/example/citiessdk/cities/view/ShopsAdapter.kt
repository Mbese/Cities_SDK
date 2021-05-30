package com.example.citiessdk.cities.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView
import com.example.citiessdk.R
import com.example.citiessdk.cities.data.Shop

class ShopsAdapter(
    private val shops: ArrayList<Shop>
) : RecyclerView.Adapter<ShopsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val rootView =
            getLayoutInflater(parent.context).inflate(R.layout.city_item_list, parent, false)
        return MyViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return shops.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val mall = shops[position]
        holder.cityTextView.text = mall.name
    }

    @VisibleForTesting
    internal fun getLayoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cityTextView: TextView = itemView.findViewById(R.id.city)
    }
}
