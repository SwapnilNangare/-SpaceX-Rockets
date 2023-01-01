package com.example.spacexrockets.api

import com.example.spacexrockets.models.rocketdetails.RocketDetailsModel
import com.example.spacexrockets.models.rocketlist.MainRocketModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GetRocketService {

    @GET("/v4/rockets")
    suspend fun getRocketsInfo(): Response<MainRocketModel>

    @GET("/v4/rockets/{id}")
    suspend fun getRocketsDetails(@Path("id") id: String): Response<RocketDetailsModel>

}