package com.example.spacexrockets.models.rocketdetails

data class FirstStage(
    var burn_time_sec: Double,
    var engines: Int,
    var fuel_amount_tons: Double,
    var reusable: Boolean,
    var thrust_sea_level: ThrustSeaLevel,
    var thrust_vacuum: ThrustVacuum
)