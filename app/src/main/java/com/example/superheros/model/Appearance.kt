package com.example.superheros.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Appearance(val gender:String, val race: String,
                      @Embedded val height: ArrayList<String>?, @Embedded val weight: ArrayList<String>?,
                      @SerializedName("eye-color") val eyeColor: String, @SerializedName("hair-color") val hairColor: String) :
    Serializable