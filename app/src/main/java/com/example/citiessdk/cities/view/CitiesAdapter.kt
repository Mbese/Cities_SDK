package com.example.citiessdk.cities.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView
import com.example.citiessdk.R
import com.example.citiessdk.cities.data.City
import com.example.citiessdk.cities.data.Mall

class CitiesAdapter(
    private val cities: ArrayList<City>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<CitiesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val rootView =
            getLayoutInflater(parent.context).inflate(R.layout.city_item_list, parent, false)
        return MyViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val city = cities[position]
        holder.cityTextView.text = city.name
        holder.cityTextView.setOnClickListener { itemClickListener.onItemClicked(city.malls) }
    }

    @VisibleForTesting
    internal fun getLayoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }

    interface ItemClickListener {
        fun onItemClicked(malls: List<Mall>)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cityTextView: TextView = itemView.findViewById(R.id.city)
    }
}