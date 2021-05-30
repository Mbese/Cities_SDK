package com.example.citiessdk.cities.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "city")
data class City(
    @PrimaryKey
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("name") val name: String,
    @Embedded val malls: ArrayList<Mall>
)