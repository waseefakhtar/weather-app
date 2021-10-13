package com.waseefakhtar.weatherapp.data.repository

import com.waseefakhtar.weatherapp.data.remote.OpenWeatherApi
import com.waseefakhtar.weatherapp.data.remote.dto.WeatherDto
import com.waseefakhtar.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: OpenWeatherApi
): WeatherRepository {

    override suspend fun getWeatherList(): List<WeatherDto> {
        return api.getCityInfo().list
    }
}