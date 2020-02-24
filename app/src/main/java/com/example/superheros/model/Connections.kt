package com.example.superheros.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Connections (@SerializedName("group-affiliation") val groupAffiliation : String, val relatives: String) :
    Serializable