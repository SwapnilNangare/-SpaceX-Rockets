package com.example.spacexrockets.models.rocketlist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "rocketTable")
data class MainRocketModelItem(

    @ColumnInfo(name = "first_name") val firstName: String?,
    var active: Boolean,
    var boosters: Int,
    var company: String,
    var cost_per_launch: Int,
    var country: String,
    var description: String,
    var diameter: Diameter,
    var engines: Engines,
    var first_flight: String,
    var first_stage: FirstStage,
    var flickr_images: List<String>,
    var height: Height,
    @PrimaryKey
    var id: String,
    var landing_legs: LandingLegs,
    var mass: Mass,
    var name: String,
    var payload_weights: List<PayloadWeight>,
    var second_stage: SecondStage,
    var stages: Int,
    var success_rate_pct: Int,
    var type: String,
    var wikipedia: String
)