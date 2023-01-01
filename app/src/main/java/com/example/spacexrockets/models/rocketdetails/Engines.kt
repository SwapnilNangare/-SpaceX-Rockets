package com.example.spacexrockets.models.rocketdetails

data class Engines(
    var engine_loss_max: Double,
    var isp: Isp,
    var layout: String,
    var number: Int,
    var propellant_1: String,
    var propellant_2: String,
    var thrust_sea_level: ThrustSeaLevel,
    var thrust_to_weight: Double,
    var thrust_vacuum: ThrustVacuum,
    var type: String,
    var version: String
)