package com.waseefakhtar.weatherapp.data.remote.dto

data class CityInfoDto(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherDto>,
    val message: Double
)