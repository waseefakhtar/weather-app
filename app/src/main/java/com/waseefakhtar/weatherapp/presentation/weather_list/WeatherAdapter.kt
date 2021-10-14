package com.waseefakhtar.weatherapp.presentation.weather_list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.waseefakhtar.weatherapp.R
import com.waseefakhtar.weatherapp.domain.model.Weather
import java.text.SimpleDateFormat
import java.util.*

class WeatherAdapter(
    private val layoutInflater: LayoutInflater,
    private val onWeatherClick: (weather: Weather) -> Unit
) : RecyclerView.Adapter<WeatherViewHolder>() {

    private var weatherList = mutableListOf<Weather>()

    override fun getItemCount(): Int = weatherList.size
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) = holder.bind(weatherList[position])
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder = WeatherViewHolder(layoutInflater, parent, onWeatherClick)

    fun add(weatherList: List<Weather>) {
        this.weatherList.addAll(weatherList)
        notifyDataSetChanged()
    }
}

class WeatherViewHolder(
    layoutInflater: LayoutInflater,
    parentView: ViewGroup,
    private val onWeatherClick: (weather: Weather) -> Unit
) : RecyclerView.ViewHolder(layoutInflater.inflate(R.layout.item_weather, parentView, false)) {
    private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
    private val weatherTextView: TextView = itemView.findViewById(R.id.weatherTextView)
    private val weatherIcon: ImageView = itemView.findViewById(R.id.imageView)
    fun bind(weather: Weather) {
        itemView.setOnClickListener { onWeatherClick(weather) }
        dateTextView.text = weather.dt.toDate()
        weatherTextView.text = weather.day.toString() + "°C"
        weatherIcon.load("https://openweathermap.org/img/w/${weather.icon}.png")
    }
}

private fun Int.toDate(): String? {
    try {
        val sdf = SimpleDateFormat("EEEE, MMM dd")
        val netDate = Date(this.toLong() * 1000)
        return sdf.format(netDate)
    } catch (e: Exception) {
        return e.toString()
    }
}