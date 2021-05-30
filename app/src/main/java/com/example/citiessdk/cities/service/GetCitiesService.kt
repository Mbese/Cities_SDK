package com.example.citiessdk.cities.service

import com.example.citiessdk.cities.data.Cities
import retrofit2.Response
import retrofit2.http.GET

interface GetCitiesService {
    @GET("v2/5b7e8bc03000005c0084c210")
    suspend fun getCities(): Response<Cities>
}