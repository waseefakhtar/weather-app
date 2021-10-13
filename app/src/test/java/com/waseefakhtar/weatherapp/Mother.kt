package com.waseefakhtar.weatherapp

import com.google.gson.annotations.SerializedName
import com.waseefakhtar.weatherapp.data.remote.dto.City
import com.waseefakhtar.weatherapp.data.remote.dto.CityInfoDto
import com.waseefakhtar.weatherapp.data.remote.dto.Coord
import com.waseefakhtar.weatherapp.data.remote.dto.FeelsLike
import com.waseefakhtar.weatherapp.data.remote.dto.Temp
import com.waseefakhtar.weatherapp.data.remote.dto.WeatherDto
import com.waseefakhtar.weatherapp.data.remote.dto.WeatherInfo
import com.waseefakhtar.weatherapp.domain.model.Weather
import java.util.concurrent.ThreadLocalRandom

private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
val random
    get() = ThreadLocalRandom.current()

fun generateWeatherDtoList(
    size: Int = randomPositiveInt(10),
    creationFunction: (Int) -> WeatherDto = { generateWeatherDto() }
): List<WeatherDto> = (0..size).map { creationFunction(it) }

private fun generateWeatherDto(): WeatherDto =
    WeatherDto(
        clouds = randomInt(),
        deg = randomInt(),
        dt = randomInt(),
        feelsLike = generateFeelsLike(),
        gust = randomDouble(),
        humidity = randomInt(),
        pop = randomDouble(),
        pressure = randomInt(),
        speed = randomDouble(),
        sunrise = randomInt(),
        sunset = randomInt(),
        temp = generateTemp(),
        weatherInfo = generateWeatherInfoList()
    )

private fun generateFeelsLike(): FeelsLike {
    return FeelsLike(
        day = randomDouble(),
        eve = randomDouble(),
        morn = randomDouble(),
        night = randomDouble()
    )
}

private fun generateTemp(): Temp {
    return Temp(
        day = randomDouble(),
        eve = randomDouble(),
        max = randomDouble(),
        min = randomDouble(),
        morn = randomDouble(),
        night = randomDouble()
    )
}

private fun generateWeatherInfoList(
    size: Int = randomPositiveInt(10),
    creationFunction: (Int) -> WeatherInfo = { generateWeatherInfo() }
): List<WeatherInfo> = (0..size).map { creationFunction(it) }

private fun generateWeatherInfo(): WeatherInfo =
    WeatherInfo(
        description = randomString(),
        icon = randomString(),
        id = randomInt(),
        main = randomString()
    )

fun generateCityInfoDto(): CityInfoDto =
    CityInfoDto(
        city = generateCity(),
        cnt = randomInt(),
        cod = randomString(),
        list = generateWeatherDtoList(),
        message = randomDouble()
    )

private fun generateCity(): City =
    City(
        coord = Coord(randomDouble(), randomDouble()),
        country = randomString(),
        id = randomInt(),
        name = randomString(),
        population = randomInt(),
        timezone = randomInt()
    )

fun randomPositiveInt(maxInt: Int = Int.MAX_VALUE - 1): Int = random.nextInt(maxInt + 1).takeIf { it > 0 } ?: randomPositiveInt(maxInt)
fun randomInt() = random.nextInt()
fun randomDouble() = random.nextDouble()
fun randomString(size: Int = 20): String = (0..size)
    .map { charPool[random.nextInt(0, charPool.size)] }
    .joinToString("")