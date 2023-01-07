package com.example.spacexrockets.models.rocketlist

import androidx.room.Entity
import androidx.room.PrimaryKey

data class MainRocketModelItem(
    var id: String,
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
    var landing_legs: LandingLegs,
    var mass: Mass,
    var name: String,
    var second_stage: SecondStage,
    var stages: Int,
    var success_rate_pct: Int,
    var type: String,
    var wikipedia: String
) {
    fun toRocketEntity(): RocketEntity {
        return RocketEntity(
            id = id,
            name = name,
            description = description,
            country = country,
            engineCount = engines.number,
            images = flickr_images
        )
    }
}

@Entity
data class RocketEntity(
    @PrimaryKey
    val id: String,
    val name:String,
    var description: String,
    val country: String,
    val engineCount: Int,
    val images: List<String>
)