package com.example.spacexrockets.models.rocketdetails

data class RocketDetailsModel(
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