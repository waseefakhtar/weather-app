package com.waseefakhtar.weatherapp.data.remote.dto

data class CityInfoDto(
    val city: CityDto,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherDto>,
    val message: Double
)