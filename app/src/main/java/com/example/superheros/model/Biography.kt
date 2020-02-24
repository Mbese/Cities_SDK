package com.example.superheros.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Biography(@SerializedName("full-name") val fullName: String, @SerializedName("alter-egos") val alterEgos: String,
                     @Embedded val aliases: ArrayList<String>?, @SerializedName("place-of-birth") val placeOfBirth: String,
                     @SerializedName("first-appearance") val firstAppearance: String, val publisher: String, val alignment: String) :
    Serializable