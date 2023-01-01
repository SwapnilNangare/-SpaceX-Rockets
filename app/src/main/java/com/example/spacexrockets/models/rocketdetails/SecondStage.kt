package com.example.spacexrockets.models.rocketdetails

data class SecondStage(
    var burn_time_sec: Double,
    var engines: Double,
    var fuel_amount_tons: Double,
    var payloads: Payloads,
    var reusable: Boolean,
    var thrust: Thrust
)