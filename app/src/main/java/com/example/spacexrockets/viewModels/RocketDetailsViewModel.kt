package com.example.spacexrockets.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexrockets.models.rocketdetails.RocketDetailsModel
import com.example.spacexrockets.repository.RocketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RocketDetailsViewModel(private val repository: RocketRepository, id: String) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFullDetailsOfRocket(id)

        }
    }

    val allDetailsOfRockets: LiveData<RocketDetailsModel>
        get() = repository.rocketsInformationDetails
}