package com.example.spacexrockets.models

data class SecondStage(
    var burn_time_sec: Int,
    var engines: Int,
    var fuel_amount_tons: Double,
    var payloads: Payloads,
    var reusable: Boolean,
    var thrust: Thrust
)