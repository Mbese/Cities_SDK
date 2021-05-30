package com.example.citiessdk.cities.api

import com.example.citiessdk.cities.service.GetCitiesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        const val BASE_URL = "https://www.mocky.io/"
        private fun getRetrofitInstance(): Retrofit.Builder {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
        }

        fun getCitiesService(): GetCitiesService {
            return getRetrofitInstance().build().create(GetCitiesService::class.java)
        }
    }
}
