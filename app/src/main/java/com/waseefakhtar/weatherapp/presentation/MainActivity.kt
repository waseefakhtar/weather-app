package com.waseefakhtar.weatherapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.waseefakhtar.weatherapp.databinding.ActivityMainBinding
import com.waseefakhtar.weatherapp.presentation.weather_list.WeatherAdapter
import com.waseefakhtar.weatherapp.presentation.weather_list.WeatherListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: WeatherListViewModel by viewModels()
    private val adapter: WeatherAdapter by lazy { WeatherAdapter(layoutInflater, ::onWeatherClick) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        subscribeToStates()
    }

    private fun initViews() {
        binding.recyclerView.adapter = adapter
    }

    private fun subscribeToStates() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                when (state.isLoading) {
                    true -> binding.progressBar.visibility = View.VISIBLE
                    false -> binding.progressBar.visibility = View.GONE
                }

                when (state.weatherList.isNotEmpty()) {
                    true -> adapter.add(state.weatherList.map { it.dt.toDate() ?: "" })
                }

                when (state.error.isNotEmpty()) {
                    true -> Toast.makeText(this@MainActivity, state.error, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun Int.toDate(): String? {
        try {
            val sdf = SimpleDateFormat("EEEE, MMMM dd")
            val netDate = Date(this.toLong() * 1000)
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }

    private fun onWeatherClick(weather: String) {

    }
}