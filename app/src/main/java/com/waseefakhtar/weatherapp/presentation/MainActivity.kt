package com.waseefakhtar.weatherapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.waseefakhtar.weatherapp.R
import com.waseefakhtar.weatherapp.databinding.ActivityMainBinding
import com.waseefakhtar.weatherapp.domain.model.Weather
import com.waseefakhtar.weatherapp.presentation.weather_list.WeatherAdapter
import com.waseefakhtar.weatherapp.presentation.weather_list.WeatherListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}