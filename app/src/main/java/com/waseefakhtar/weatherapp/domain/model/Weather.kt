package com.waseefakhtar.weatherapp.domain.model

import com.waseefakhtar.weatherapp.data.remote.dto.Temp
import com.waseefakhtar.weatherapp.data.remote.dto.WeatherInfo

data class Weather(
    val dt: Int,
    val day: Double,
    val night: Double,
    val title: String,
    val description: String,
    val icon: String,
)
