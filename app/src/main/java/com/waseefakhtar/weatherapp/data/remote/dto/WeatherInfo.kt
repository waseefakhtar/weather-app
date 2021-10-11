package com.waseefakhtar.weatherapp.data.remote.dto

data class WeatherInfo(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)