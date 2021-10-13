package com.waseefakhtar.weatherapp.domain.repository

import com.waseefakhtar.weatherapp.data.remote.dto.WeatherDto
import com.waseefakhtar.weatherapp.domain.model.Weather

interface WeatherRepository {
    // TODO: Add caching

    suspend fun getWeatherList(): List<WeatherDto>
}