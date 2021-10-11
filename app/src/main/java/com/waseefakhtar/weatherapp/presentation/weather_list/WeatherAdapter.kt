package com.waseefakhtar.weatherapp.presentation.weather_list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.waseefakhtar.weatherapp.R

class WeatherAdapter(
    private val layoutInflater: LayoutInflater,
    private val onWeatherClick: (weather: String) -> Unit
) : RecyclerView.Adapter<WeatherViewHolder>() {

    private var weatherList = mutableListOf<String>()

    override fun getItemCount(): Int = weatherList.size
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) = holder.bind(weatherList[position])
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder = WeatherViewHolder(layoutInflater, parent, onWeatherClick)

    fun add(weatherList: List<String>) {
        this.weatherList.addAll(weatherList)
        notifyDataSetChanged()
    }
}

class WeatherViewHolder(
    layoutInflater: LayoutInflater,
    parentView: ViewGroup,
    private val onWeatherClick: (weather: String) -> Unit
) : RecyclerView.ViewHolder(layoutInflater.inflate(R.layout.item_weather, parentView, false)) {
    private val textView: TextView = itemView.findViewById(R.id.textView)
    fun bind(weather: String) {
        itemView.setOnClickListener { onWeatherClick(weather) }
        textView.text = weather
    }
}