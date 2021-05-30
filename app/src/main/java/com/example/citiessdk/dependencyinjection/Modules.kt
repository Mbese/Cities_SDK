package com.example.citiessdk.dependencyinjection

import androidx.room.Room
import com.example.citiessdk.cities.api.ApiClient
import com.example.citiessdk.cities.api.ApiClient.Companion.BASE_URL
import com.example.citiessdk.cities.repo.CitiesRepo
import com.example.citiessdk.cities.service.GetCitiesService
import com.example.citiessdk.cities.viewmodel.CitiesViewModel
import com.example.citiessdk.database.AppDatabase
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel {
        CitiesViewModel(get())
    }
}

val repoModule = module {
    single {
        CitiesRepo(get(), get())
    }
}

val apiModule = module {
    fun provideApi(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }

    single { provideApi(get()) }
}

val persistenceModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "city.db").build()
    }
    single { get<AppDatabase>().citiesDao() }
}

val retrofitModule = module {
    fun retrofit(baseUrl: String) = Retrofit.Builder()
        .callFactory(OkHttpClient.Builder().build())
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun okHttp() = OkHttpClient.Builder()
        .build()

    single {
        okHttp()
    }
    single {
        retrofit(BASE_URL)
    }
    single {
        get<Retrofit>().create(GetCitiesService::class.java)
    }
}