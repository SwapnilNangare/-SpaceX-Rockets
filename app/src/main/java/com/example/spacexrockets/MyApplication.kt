package com.example.spacexrockets

import android.app.Application
import com.example.spacexrockets.api.GetRocketService
import com.example.spacexrockets.api.RetrofitHelper
import com.example.spacexrockets.repository.RocketRepository
import com.example.spacexrockets.room.RocketDatabase

class MyApplication:Application() {

    lateinit var rocketRepository: RocketRepository

    override fun onCreate() {
        super.onCreate()


        val getRockets = RetrofitHelper.getInstance().create(GetRocketService::class.java)

       val database = RocketDatabase.getDatabase(applicationContext)
        rocketRepository = RocketRepository(getRockets,database)



    }
}