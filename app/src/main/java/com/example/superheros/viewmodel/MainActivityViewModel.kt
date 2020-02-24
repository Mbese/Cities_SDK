package com.example.superheros.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.superheros.model.SuperHero
import com.example.superheros.repository.SuperHeroRepository

class MainActivityViewModel (application: Application) : AndroidViewModel(application) {
    private val repo: SuperHeroRepository = SuperHeroRepository(application)
    val superHeros: MutableLiveData<List<SuperHero>>?

    init {
        superHeros = repo.superhero
    }
}

sealed class ViewState {
    object Loading : ViewState()
    data class Error(val throwable: Throwable) : ViewState()
    data class Success(val data: Any) : ViewState()
}