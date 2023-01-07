package com.example.spacexrockets.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.spacexrockets.api.GetRocketService
import com.example.spacexrockets.models.rocketdetails.RocketDetailsModel
import com.example.spacexrockets.models.rocketlist.RocketEntity
import com.example.spacexrockets.room.RocketDatabase
import com.example.spacexrockets.utils.Resource
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

class RocketRepository(private val getRockets: GetRocketService,private val rocketDatabase: RocketDatabase) {

    val rocketsLiveData: MutableLiveData<Resource<RocketEntity>> = MutableLiveData()


    private val rocketsDetailsLiveData = MutableLiveData<RocketDetailsModel>()

    val rocketsInformationDetails: LiveData<RocketDetailsModel>
        get() = rocketsDetailsLiveData

    private val _rocketEntityList = MutableLiveData<Resource<List<RocketEntity>>>()
    val rocketEntityList: LiveData<Resource<List<RocketEntity>>> get() = _rocketEntityList

    suspend fun getRockets() {
        _rocketEntityList.postValue(Resource.Loading())
        try {
            val result = getRockets.getRocketsInfo()
            result.body()?.let {mainRocketModelList ->

                val rocketList = mutableListOf<RocketEntity>()
                mainRocketModelList.forEach {
                    rocketList.add(it.toRocketEntity())
                }

                rocketDatabase.rocketDao().saveRocketList(rocketList)
                _rocketEntityList.postValue(Resource.Success(rocketList))
            }
        } catch (e: Exception) {
            when (e) {
                is IOException -> {
                    e.printStackTrace()
                    _rocketEntityList.postValue(Resource.Error("No Internet Connection", data = rocketDatabase.rocketDao().getRocketList()))
                }
                is HttpException -> {
                    e.printStackTrace()
                    _rocketEntityList.postValue(Resource.Error("Something went wrong!"))
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

    companion object {
        private const val TAG = "RocketRepository"
    }

}