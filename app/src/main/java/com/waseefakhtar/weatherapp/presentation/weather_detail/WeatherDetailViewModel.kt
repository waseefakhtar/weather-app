package com.waseefakhtar.weatherapp.presentation.weather_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waseefakhtar.weatherapp.common.Constants.PARAM_DATE
import com.waseefakhtar.weatherapp.common.Resource
import com.waseefakhtar.weatherapp.domain.use_case.get_weather_detail.GetWeatherDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WeatherDetailViewModel @Inject constructor(
    private val getWeatherDetailUseCase: GetWeatherDetailUseCase
): ViewModel() {

    private val _state = MutableSharedFlow<WeatherState>()
    val state = _state.asSharedFlow()

    fun getWeatherBy(date: Int) {
        getWeatherDetailUseCase(date).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.emit(WeatherState(weather = result.data))
                }

                is Resource.Error -> {
                    _state.emit(WeatherState(error = result.message ?: "An unexpected error occurred"))
                }
                is Resource.Loading -> {
                    _state.emit(WeatherState(isLoading = true))

                }
            }
        }.launchIn(viewModelScope)
    }

}