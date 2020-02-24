package com.example.superheros.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroClientApi {
    companion object {
        private val baseUrl = "https://www.superheroapi.com/api.php/550591699147802/"

        fun getRetrofitInstance() : Retrofit.Builder {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
        }

        fun getService(): SuperHeroApiService {
            return getRetrofitInstance().build().create(SuperHeroApiService::class.java)
        }
    }
}