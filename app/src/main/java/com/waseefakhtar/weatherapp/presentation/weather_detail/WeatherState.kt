package com.waseefakhtar.weatherapp.presentation.weather_detail

import com.waseefakhtar.weatherapp.domain.model.Weather

data class WeatherState(
    val isLoading: Boolean = false,
    val weather: Weather? = null,
    val error: String = ""
)
