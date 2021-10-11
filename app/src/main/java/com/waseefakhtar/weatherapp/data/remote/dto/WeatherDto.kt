package com.waseefakhtar.weatherapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.waseefakhtar.weatherapp.domain.model.Weather

data class WeatherDto(
    val clouds: Int,
    val deg: Int,
    val dt: Int,
    @SerializedName("feels_like")
    val feelsLike: FeelsLike,
    val gust: Double,
    val humidity: Int,
    val pop: Int,
    val pressure: Int,
    val speed: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val weatherInfo: List<WeatherInfo>
)

fun WeatherDto.toWeather(): Weather {
    return Weather(
        dt = dt,
        day = temp.day,
        night = temp.night,
        title = weatherInfo.first().main,
        description = weatherInfo.first().description,
        icon = weatherInfo.first().icon
    )
}