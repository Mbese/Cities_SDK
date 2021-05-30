package com.example.citiessdk.cities.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shop(
    val id: Int,
    val name: String
) : Parcelable