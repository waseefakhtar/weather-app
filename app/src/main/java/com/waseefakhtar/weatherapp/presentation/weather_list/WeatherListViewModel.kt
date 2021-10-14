package com.waseefakhtar.weatherapp.presentation.weather_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waseefakhtar.weatherapp.common.Resource
import com.waseefakhtar.weatherapp.domain.use_case.get_weather_list.GetWeatherListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModel @Inject constructor(
    private val getWeatherListUseCase: GetWeatherListUseCase
): ViewModel() {

    private val _state = MutableStateFlow<WeatherListState>(WeatherListState())
    val state = _state.asStateFlow()

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