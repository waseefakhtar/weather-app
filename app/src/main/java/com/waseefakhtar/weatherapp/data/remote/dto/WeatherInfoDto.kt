package com.waseefakhtar.weatherapp.data.remote.dto

data class WeatherInfoDto(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)