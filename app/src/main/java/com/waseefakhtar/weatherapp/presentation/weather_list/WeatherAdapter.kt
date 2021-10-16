package com.waseefakhtar.weatherapp.presentation.weather_list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.waseefakhtar.weatherapp.R
import com.waseefakhtar.weatherapp.databinding.ItemWeatherBinding
import com.waseefakhtar.weatherapp.domain.model.Weather
import com.waseefakhtar.weatherapp.presentation.BaseRecyclerViewAdapter
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class WeatherAdapter @Inject constructor() : BaseRecyclerViewAdapter<Weather, ItemWeatherBinding>() {

    override fun setBinding(parent: ViewGroup): ItemWeatherBinding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun onBindData(
        holder: Companion.BaseViewHolder<ItemWeatherBinding>,
        item: Weather,
        position: Int
    ) {
        holder.binding.apply {
            itemView.setOnClickListener {
                onItemClick?.invoke(item)
            }
            dateTextView.text = item.dt.toDate()
            weatherTextView.text = item.day.toString() + "°C"
            imageView.load("https://openweathermap.org/img/w/${item.icon}.png")
        }
    }

    override fun setItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return  oldItem.title == newItem.title
    }

    override fun serContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return  oldItem == newItem
    }

    override var differ: AsyncListDiffer<Weather> = AsyncListDiffer(this, differCallBack)

}

private fun Int.toDate(): String? {
    return try {
        val sdf = SimpleDateFormat("EEEE, MMM dd")
        val netDate = Date(this.toLong() * 1000)
        sdf.format(netDate)
    } catch (e: Exception) {
        e.toString()
    }
}