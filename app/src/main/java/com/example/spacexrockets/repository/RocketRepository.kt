package com.example.spacexrockets.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.spacexrockets.api.GetRocketService
import com.example.spacexrockets.models.rocketdetails.RocketDetailsModel
import com.example.spacexrockets.models.rocketlist.MainRocketModel
import com.example.spacexrockets.room.RocketDatabase
import com.example.spacexrockets.utils.MyUtils
import com.example.spacexrockets.utils.Resource
import retrofit2.HttpException
import java.io.IOException

class RocketRepository(private val getRockets: GetRocketService,private val rocketDatabase: RocketDatabase) {

    val rocketsLiveData: MutableLiveData<Resource<MainRocketModel>> = MutableLiveData()
    private val rocketsDetailsLiveData = MutableLiveData<RocketDetailsModel>()
    val rocketsInformationDetails: LiveData<RocketDetailsModel>
        get() = rocketsDetailsLiveData


    suspend fun getRockets() {


        rocketsLiveData.postValue(Resource.Loading())
        try {
            val result = getRockets.getRocketsInfo()
            result.body()?.let {

             rocketDatabase.rocketDao().insetRocketDetails(result.body()!!)

                rocketsLiveData.postValue(Resource.Success(it))
            }
        } catch (e: Exception) {
            when (e) {
                is IOException -> {
                    e.printStackTrace()
                    rocketsLiveData.postValue(Resource.Error("No Internet Connection"))
                }
                is HttpException -> {
                    e.printStackTrace()
                    rocketsLiveData.postValue(Resource.Error("Something went wrong!"))
                }
            }
        }
    }

    suspend fun getFullDetailsOfRocket(id: String) {
        val result = getRockets.getRocketsDetails(id)
        if (result?.body() != null) {
            rocketsDetailsLiveData.postValue(result.body())
        }
    }


}