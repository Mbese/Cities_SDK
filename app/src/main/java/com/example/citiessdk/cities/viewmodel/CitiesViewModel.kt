package com.example.citiessdk.cities.viewmodel

import androidx.lifecycle.ViewModel
import com.example.citiessdk.cities.repo.CitiesRepo

class CitiesViewModel(repo: CitiesRepo) : ViewModel() {
    val cities = repo.cityList
}