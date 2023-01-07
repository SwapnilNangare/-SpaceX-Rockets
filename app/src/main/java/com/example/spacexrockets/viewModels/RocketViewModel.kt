package com.example.spacexrockets.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexrockets.models.rocketlist.MainRocketModel
import com.example.spacexrockets.models.rocketlist.RocketEntity
import com.example.spacexrockets.repository.RocketRepository
import com.example.spacexrockets.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RocketViewModel(private val repository: RocketRepository) : ViewModel() {
    val rockets: MutableLiveData<Resource<List<RocketEntity>>>
        get() = repository.rocketEntityList as MutableLiveData<Resource<List<RocketEntity>>>

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRockets()
        }
    }
}