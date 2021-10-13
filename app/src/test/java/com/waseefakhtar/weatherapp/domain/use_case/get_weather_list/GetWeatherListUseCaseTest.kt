package com.waseefakhtar.weatherapp.domain.use_case.get_weather_list

import com.waseefakhtar.weatherapp.common.Resource
import com.waseefakhtar.weatherapp.data.remote.dto.*
import com.waseefakhtar.weatherapp.data.repository.FakeWeatherRepository
import com.waseefakhtar.weatherapp.domain.model.Weather
import com.waseefakhtar.weatherapp.generateWeatherDtoList
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class GetWeatherListUseCaseTest {

    private lateinit var getWeatherListUseCase: GetWeatherListUseCase
    private lateinit var fakeWeatherRepository: FakeWeatherRepository

    @Before
    fun setUp() {
        fakeWeatherRepository = FakeWeatherRepository()
        getWeatherListUseCase = GetWeatherListUseCase(fakeWeatherRepository)
    }

    @Test
    fun `Should return weatherDtoList successfully`() = runBlockingTest {
        val weatherDtoList = generateWeatherDtoList()
        val weatherList = weatherDtoList.map { it.toWeather() }
        val expectedResult = flow<Resource<List<Weather>>> {
            emit(Resource.Loading<List<Weather>>())
            emit(Resource.Success<List<Weather>>(weatherList))
        }

        fakeWeatherRepository.initList(weatherDtoList)

        val result = getWeatherListUseCase.invoke()

        result.first().data.`should equal`(expectedResult.first().data)
        result.last().data.`should equal`(expectedResult.last().data)
    }

    @Test
    fun `Should return exception when getting weatherList is unsuccessful`() = runBlockingTest {
        val expectedResult = flow<Resource<List<Weather>>> {
            emit(Resource.Loading<List<Weather>>())
            emit(Resource.Error<List<Weather>>("Couldn't reach server. Check your internet connection"))
        }

        fakeWeatherRepository.setShouldReturnNetworkError(true)

        try {
            val result = getWeatherListUseCase.invoke()
            result.first().data.`should equal`(expectedResult.first().data)
            result.last().data.`should equal`(expectedResult.last().data)
            result.first().message.`should equal`(expectedResult.first().message)
            result.last().message.`should equal`(expectedResult.last().message)
        } catch (e: Exception) { }
    }
}