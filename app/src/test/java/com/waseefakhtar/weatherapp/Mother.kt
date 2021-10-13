package com.waseefakhtar.weatherapp

import com.google.gson.annotations.SerializedName
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


fun randomPositiveInt(maxInt: Int = Int.MAX_VALUE - 1): Int = random.nextInt(maxInt + 1).takeIf { it > 0 } ?: randomPositiveInt(maxInt)
fun randomPositiveLong(maxLong: Long = Long.MAX_VALUE - 1): Long = random.nextLong(maxLong + 1).takeIf { it > 0 } ?: randomPositiveLong(maxLong)
fun randomInt() = random.nextInt()
fun randomIntBetween(min: Int, max: Int) = random.nextInt(max - min) + min
fun randomLong() = random.nextLong()
fun randomDouble() = random.nextDouble()
fun randomBoolean() = random.nextBoolean()
fun randomString(size: Int = 20): String = (0..size)
    .map { charPool[random.nextInt(0, charPool.size)] }
    .joinToString("")