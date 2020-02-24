package com.example.superheros.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.superheros.R
import com.example.superheros.model.SuperHero

class SuperHerosAdapter(
    private val context: Context,
    private val superHeros: ArrayList<SuperHero>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<SuperHerosAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val rootView = getLayoutInflater(parent.context).inflate(R.layout.list_row, parent, false)
        return MyViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return superHeros.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val superHero = superHeros[position]
        holder.name.text = superHero.name
        holder.fullName.text = superHero.biography.fullName

        Glide.with(context).load(superHero.image.url).into(holder.thumbnail)

        holder.itemView.setOnClickListener { itemClickListener.onItemClicked(superHero) }
    }

    @VisibleForTesting
    internal fun getLayoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }

    interface ItemClickListener {
        fun onItemClicked(model: SuperHero)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var fullName: TextView = itemView.findViewById(R.id.fullName)
        var thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
    }
}