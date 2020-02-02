package main.master.machinetest.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import main.master.machinetest.R
import main.master.machinetest.data.network.MyApi
import main.master.machinetest.data.repository.WeatherRepository
import main.master.machinetest.databinding.ActivityWeatherBinding

class WeatherActivity : AppCompatActivity() {


    private lateinit var factory: WeatherViewModelFactory
    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val binding : ActivityWeatherBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_weather)
        val api = MyApi()
        val repository = WeatherRepository(api)
        factory = WeatherViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this,factory).get(WeatherViewModel::class.java)


        viewModel.getWeather()
        viewModel.weather.observe(this, Observer {
                weather ->
            R.layout.activity_weather.also {
                binding.viewmodel = weather
                Log.i("weatherresponse2",weather.main.temp.toString())
            }
        })


    }
}
