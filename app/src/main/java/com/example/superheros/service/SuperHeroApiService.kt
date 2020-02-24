package com.example.superheros.service

import com.example.superheros.model.SuperHero
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroApiService {
    @GET("{id}/")
    suspend fun getSuperHero(@Path("id") id: Int?): Response<SuperHero>
}