package com.example.superheros.repository

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.superheros.model.AppDatabase
import com.example.superheros.model.SuperHero
import com.example.superheros.service.SuperHeroApiService
import com.example.superheros.service.SuperHeroClientApi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class SuperHeroRepository(
    private val app: Application,
    private val apiService: SuperHeroApiService = SuperHeroClientApi.getService()
) {

    val superhero = MutableLiveData<List<SuperHero>>()
    private val superHeroDao = AppDatabase.getInstance(app).superherosDoa()

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("", "$exception handled !")
    }

    init {
        CoroutineScope(Dispatchers.IO + handler).launch {
            val data = superHeroDao.getSuperHeros()
            if (data.isEmpty()) {
                getHero()
            } else {
                superhero.postValue(data)
            }
        }
    }

    @WorkerThread
    suspend fun getHero() {
        if (networkAvailable()) {
            val list = ArrayList<SuperHero>()
            var superHeroData: SuperHero?
            try {
                for (i in 1..8) {
                    superHeroData = apiService.getSuperHero(i).body()
                    superHeroData?.let {
                        list.add(it)
                        superHeroDao.insertSuperHero(it)
                    }
                }
                superhero.postValue(list)
            } catch (e: Exception) {

            }
        }
    }

    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager =
            app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }
}