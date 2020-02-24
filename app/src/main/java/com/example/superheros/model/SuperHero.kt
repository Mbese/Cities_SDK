package com.example.superheros.model

import android.widget.ImageView
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.Glide
import androidx.databinding.BindingAdapter

@Entity(tableName = "superheros")
data class SuperHero(@SerializedName("response") val response: String,
                     @PrimaryKey @SerializedName("id") val id: String,
                     @SerializedName("name") val name: String,
                     @Embedded(prefix = "powerstats_")
                     @SerializedName("powerstats") val powerstats: Powerstats,
                     @Embedded(prefix = "biography_")
                     @SerializedName("biography") val biography: Biography,
                     @Embedded(prefix = "appearance_")
                     @SerializedName("appearance") val appearance: Appearance,
                     @Embedded(prefix = "work_")
                     @SerializedName("work") val work: Work,
                     @Embedded(prefix = "connections_")
                     @SerializedName("connections") val connections: Connections,
                     @Embedded(prefix = "image_")
                     @SerializedName("image") val image: Image) : Serializable

@BindingAdapter("profileImage")
fun loadImage(view: ImageView, imageUrl: String) {
    Glide.with(view.context)
        .load(imageUrl).apply(RequestOptions().circleCrop())
        .into(view)
}