package com.waseefakhtar.weatherapp

import com.waseefakhtar.weatherapp.data.remote.dto.CityDto
import com.waseefakhtar.weatherapp.data.remote.dto.CityInfoDto
import com.waseefakhtar.weatherapp.data.remote.dto.CoordDto
import com.waseefakhtar.weatherapp.data.remote.dto.FeelsLikeDto
import com.waseefakhtar.weatherapp.data.remote.dto.TempDto
import com.waseefakhtar.weatherapp.data.remote.dto.WeatherDto
import com.waseefakhtar.weatherapp.data.remote.dto.WeatherInfoDto
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

private fun generateFeelsLike(): FeelsLikeDto {
    return FeelsLikeDto(
        day = randomDouble(),
        eve = randomDouble(),
        morn = randomDouble(),
        night = randomDouble()
    )
}

private fun generateTemp(): TempDto {
    return TempDto(
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
    creationFunction: (Int) -> WeatherInfoDto = { generateWeatherInfo() }
): List<WeatherInfoDto> = (0..size).map { creationFunction(it) }

private fun generateWeatherInfo(): WeatherInfoDto =
    WeatherInfoDto(
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

private fun generateCity(): CityDto =
    CityDto(
        coord = CoordDto(randomDouble(), randomDouble()),
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