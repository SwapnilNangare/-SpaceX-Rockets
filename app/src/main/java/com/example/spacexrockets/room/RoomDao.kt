package com.example.spacexrockets.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.spacexrockets.models.rocketlist.MainRocketModel
import com.example.spacexrockets.models.rocketlist.MainRocketModelItem

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insetRocketDetails(mainRocketModel:ArrayList<MainRocketModelItem> )

    @Query("SELECT * FROM rocketTable")
    suspend fun getRocketDetails():ArrayList<MainRocketModelItem>

}