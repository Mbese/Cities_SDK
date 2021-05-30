package com.example.citiessdk.cities.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Mall(
    val id: Int,
    val name: String,
    val shops: @RawValue List<Shop>
) : Parcelable