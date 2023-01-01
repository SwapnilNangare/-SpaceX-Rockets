package com.example.spacexrockets.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spacexrockets.repository.RocketRepository

class RocketDetailsViewModelFactory(
    private val rocketDetailsRepository: RocketRepository,
    val id: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return RocketDetailsViewModel(rocketDetailsRepository, id) as T
    }
}