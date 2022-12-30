package com.example.spacexrockets.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexrockets.models.MainRocketModel
import com.example.spacexrockets.repository.RocketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RocketViewModel(private val repository: RocketRepository):ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getDetailsOfRocket()

        }
    }
    val allDetails:LiveData<MainRocketModel>
    get() = repository.rocketsInformation
}