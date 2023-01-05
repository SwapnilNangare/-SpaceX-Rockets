package com.example.spacexrockets.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.spacexrockets.models.rocketlist.MainRocketModel
import com.example.spacexrockets.models.rocketlist.MainRocketModelItem


@Database(entities = [MainRocketModelItem::class],version = 1)

abstract class RocketDatabase : RoomDatabase() {

    abstract  fun rocketDao():RoomDao


    companion object {

        private var INSTANCE: RocketDatabase? = null

        fun getDatabase(context: Context): RocketDatabase {

            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    RocketDatabase::class.java,
                    "RocketDatabase"
                ).build()
            }
            return INSTANCE!!
        }


    }
}