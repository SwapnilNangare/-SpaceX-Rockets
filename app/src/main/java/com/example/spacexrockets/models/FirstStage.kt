package com.example.spacexrockets.models

data class FirstStage(
    var burn_time_sec: Int,
    var engines: Int,
    var fuel_amount_tons: Double,
    var reusable: Boolean,
    var thrust_sea_level: ThrustSeaLevel,
    var thrust_vacuum: ThrustVacuum
)