package com.waseefakhtar.weatherapp.presentation.weather_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waseefakhtar.weatherapp.common.Resource
import com.waseefakhtar.weatherapp.domain.use_case.get_weather_list.GetWeatherListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModel @Inject constructor(
    private val getWeatherListUseCase: GetWeatherListUseCase
): ViewModel() {

    private val _state = MutableSharedFlow<WeatherListState>()
    val state = _state.asSharedFlow()

    init {
        getWeatherList()
    }

    private fun getWeatherList() {
        getWeatherListUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.emit(WeatherListState(weatherList = result.data ?: emptyList()))
                }

                is Resource.Error -> {
                    _state.emit(WeatherListState(error = result.message ?: "An unexpected error occurred"))
                }
                is Resource.Loading -> {
                    _state.emit(WeatherListState(isLoading = true))
                }
            }
        }.launchIn(viewModelScope)
    }

}