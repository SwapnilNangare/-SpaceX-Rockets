package com.example.spacexrockets.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.spacexrockets.api.GetRockets
import com.example.spacexrockets.models.MainRocketModel

class RocketRepository(private val getRockets: GetRockets) {

    private val rocketsLiveData=MutableLiveData<MainRocketModel>()

      val rocketsInformation:LiveData<MainRocketModel>
        get() =rocketsLiveData

    suspend fun getDetailsOfRocket(){
        val result= getRockets.GetRocketsinfo()

        if(result?.body()!=null){

            rocketsLiveData.postValue(result.body())


        }
    }




}