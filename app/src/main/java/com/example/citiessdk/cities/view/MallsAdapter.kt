package com.example.citiessdk.cities.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView
import com.example.citiessdk.R
import com.example.citiessdk.cities.data.Mall
import com.example.citiessdk.cities.data.Shop

class MallsAdapter(
    private val malls: ArrayList<Mall>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<MallsAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val rootView =
            getLayoutInflater(parent.context).inflate(R.layout.city_item_list, parent, false)
        return MyViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return malls.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val mall = malls[position]
        holder.cityTextView.text = mall.name
        holder.cityTextView.setOnClickListener { itemClickListener.onItemClicked(mall.shops) }
    }

    @VisibleForTesting
    internal fun getLayoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }

    interface ItemClickListener {
        fun onItemClicked(model: List<Shop>)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cityTextView: TextView = itemView.findViewById(R.id.city)
    }
}
