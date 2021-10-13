package com.waseefakhtar.weatherapp.data.remote

import com.waseefakhtar.weatherapp.data.remote.dto.CityInfoDto

class FakeOpenWeatherApi : OpenWeatherApi {

    private var cityInfoDto: CityInfoDto? = null

    override suspend fun getCityInfo(): CityInfoDto {
        return cityInfoDto!!
    }

    fun initCityInfoDto(cityInfoDto: CityInfoDto) {
        this.cityInfoDto = cityInfoDto
    }
}