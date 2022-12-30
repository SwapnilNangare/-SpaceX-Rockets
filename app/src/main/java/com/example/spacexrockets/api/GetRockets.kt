package com.example.spacexrockets.api

import com.example.spacexrockets.models.MainRocketModel
import retrofit2.Response
import retrofit2.http.GET

interface GetRockets {


    @GET("/v4/rockets")
    suspend fun GetRocketsinfo():Response<MainRocketModel>
}