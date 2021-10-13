package com.waseefakhtar.weatherapp.domain.use_case.get_weather_detail

import com.waseefakhtar.weatherapp.common.Resource
import com.waseefakhtar.weatherapp.data.remote.dto.toWeather
import com.waseefakhtar.weatherapp.data.repository.FakeWeatherRepository
import com.waseefakhtar.weatherapp.domain.model.Weather
import com.waseefakhtar.weatherapp.generateWeatherDtoList
import com.waseefakhtar.weatherapp.randomInt
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class GetWeatherDetailUseCaseTest {

    private lateinit var getWeatherDetailUseCase: GetWeatherDetailUseCase
    private lateinit var fakeWeatherRepository: FakeWeatherRepository

    @Before
    fun setUp() {
        fakeWeatherRepository = FakeWeatherRepository()
        getWeatherDetailUseCase = GetWeatherDetailUseCase(fakeWeatherRepository)
    }

    @Test
    fun `Should return Weather successfully`() = runBlockingTest {
        val weatherDtoList = generateWeatherDtoList()
        val weatherList = weatherDtoList.map { it.toWeather() }
        val weather = weatherList.random()
        val dateInput = weather.dt
        val expectedResult = flow {
            emit(Resource.Loading<Weather>())
            emit(Resource.Success<Weather>(weather))
        }

        fakeWeatherRepository.initList(weatherDtoList)

        val result = getWeatherDetailUseCase.invoke(dateInput)

        result.first().data.`should equal`(expectedResult.first().data)
        result.last().data.`should equal`(expectedResult.last().data)
    }

    @Test
    fun `Should return exception when getting Weather is unsuccessful`() = runBlockingTest {
        val expectedResult = flow {
            emit(Resource.Loading<Weather>())
            emit(Resource.Error<Weather>("Couldn't reach server. Check your internet connection"))
        }

        fakeWeatherRepository.setShouldReturnNetworkError(true)

        try {
            val result = getWeatherDetailUseCase.invoke(randomInt())
            result.first().data.`should equal`(expectedResult.first().data)
            result.last().data.`should equal`(expectedResult.last().data)
            result.first().message.`should equal`(expectedResult.first().message)
            result.last().message.`should equal`(expectedResult.last().message)
        } catch (e: Exception) { }
    }
}