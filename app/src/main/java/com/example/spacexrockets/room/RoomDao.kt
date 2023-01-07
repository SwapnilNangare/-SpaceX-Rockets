package com.example.spacexrockets.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.spacexrockets.models.rocketlist.RocketEntity

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRocketList(rocketList: List<RocketEntity>)

    @Query("SELECT * FROM rocketentity")
    suspend fun getRocketList():List<RocketEntity>
}