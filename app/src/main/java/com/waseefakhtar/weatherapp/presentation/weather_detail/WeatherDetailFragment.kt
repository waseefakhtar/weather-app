package com.waseefakhtar.weatherapp.presentation.weather_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.waseefakhtar.weatherapp.R
import com.waseefakhtar.weatherapp.databinding.FragmentWeatherDetailBinding
import com.waseefakhtar.weatherapp.databinding.FragmentWeatherListBinding
import com.waseefakhtar.weatherapp.domain.model.Weather
import com.waseefakhtar.weatherapp.presentation.BindingFragment
import com.waseefakhtar.weatherapp.presentation.weather_list.WeatherListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WeatherDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class WeatherDetailFragment : BindingFragment<FragmentWeatherDetailBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentWeatherDetailBinding::inflate

    val args: WeatherDetailFragmentArgs by navArgs()
    private val viewModel: WeatherDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getWeatherBy(args.dateArg)
        subscribeToStates()
    }

    private fun subscribeToStates() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                when (state.isLoading) {
                    true -> binding.progressBar.visibility = View.VISIBLE
                    false -> binding.progressBar.visibility = View.GONE
                }

                when (state.weather != null) {
                    true -> populateViews(state.weather)
                }

                when (state.error.isNotEmpty()) {
                    true -> Toast.makeText(context, state.error, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun populateViews(weather: Weather) {
        with(binding) {
            title.text = weather.title
            description.text = weather.description
            temperatureValue.text = when {
                weather.day > 25 -> "Hot"
                weather.day < 10 -> "Cold"
                else -> "Normal"
            }
            dayValue.text = weather.day.toString() + "°C"
            nightValue.text = weather.night.toString() + "°C"
        }
    }
}