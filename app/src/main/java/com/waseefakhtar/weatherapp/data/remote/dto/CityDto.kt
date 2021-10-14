package com.waseefakhtar.weatherapp.data.remote.dto

data class CityDto(
    val coord: CoordDto,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val timezone: Int
)