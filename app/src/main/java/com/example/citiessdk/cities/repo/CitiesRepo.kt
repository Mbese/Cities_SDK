package com.example.citiessdk.cities.repo

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.citiessdk.cities.api.ApiClient
import com.example.citiessdk.cities.data.City
import com.example.citiessdk.cities.data.CityDao
import com.example.citiessdk.cities.data.Mall
import com.example.citiessdk.cities.data.Shop
import com.example.citiessdk.cities.service.GetCitiesService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CitiesRepo(
    private val dao: CityDao,
    private val apiService: GetCitiesService = ApiClient.getCitiesService()
) {

    val cityList = MutableLiveData<List<City>>()

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("", "$exception handled!")
    }

    init {
        CoroutineScope(Dispatchers.IO + handler).launch {
            kotlin.runCatching {
                getCities()
            }
        }
    }

    @WorkerThread
     suspend fun getCities() {
        var cities = dao.getCities().value

        if (cities == null) {
            cities = apiService.getCities().body()?.cities
            cityList.postValue(cities)
            if (cities != null) {
                cityList.postValue(cities)
                dao.insert(cities)
            }

        } else {
            cityList.postValue(cities)
        }
    }

     suspend fun getCity(cityName: String): City? {
        val cityList = dao.getCities().value
        if (!cityList.isNullOrEmpty()) {
            for (city in cityList) {
                if (city.name == cityName) {
                    return city
                }
            }
        }
        return null
    }

     suspend fun getMallsInACity(cityName: String): ArrayList<Mall> {
        val cityList = dao.getCities().value
        if (!cityList.isNullOrEmpty()) {
            for (city in cityList) {
                if (city.name == cityName) {
                    return city.malls
                }
            }
        }
        return arrayListOf()
    }

     suspend fun getMallShops(mallName: String): List<Shop> {
        val cityList = dao.getCities().value
        if (!cityList.isNullOrEmpty()) {
            for (city in cityList) {
               for (mall in city.malls) {
                   if (mall.name == mallName) {
                       return mall.shops
                   }
               }
            }
        }
        return arrayListOf()
    }

     suspend fun getShop(mallName: String, shopName: String): Shop? {
        val cityList = dao.getCities().value
        if (!cityList.isNullOrEmpty()) {
            for (city in cityList) {
                for (m in city.malls) {
                    if (m.name == mallName) {
                        for (shop in m.shops) {
                            if (shop.name == shopName) {
                                return shop
                            }
                        }
                    }
                }
            }
        }
        return null
    }

     suspend fun getShopsInACity(cityName: String): List<Shop> {
        val cityList = dao.getCities().value
        val cityShops = ArrayList<Shop>()
        if (!cityList.isNullOrEmpty()) {
            for (city in cityList) {
                for (mall in city.malls) {
                    cityShops.addAll(mall.shops)
                }
            }
        }

        return cityShops
    }
}