package com.example.spacexrockets.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spacexrockets.repository.RocketRepository

class RocketViewModelFactory(private val repository: RocketRepository):ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RocketViewModel(repository) as T
    }

}