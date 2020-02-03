package main.master.machinetest.ui.home

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import main.master.machinetest.R
import main.master.machinetest.data.network.MyApi
import main.master.machinetest.data.repository.WeatherRepository
import main.master.machinetest.databinding.ActivityWeatherBinding

class WeatherActivity : AppCompatActivity() {

    //WeatherViewModelFactory is used for ViewModelProviders bcz FootballViewModel has constructor
    private lateinit var factory: WeatherViewModelFactory
    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityWeatherBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_weather)

        val api = MyApi()
        val repository = WeatherRepository(api)
        factory = WeatherViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this,factory).get(WeatherViewModel::class.java)


        val internet : Boolean
        internet = checkConnectivity()
        if (internet == true) {

            //viewmodel method is call where we get the data from webserice in a cycle of MVVM
            //( APIClient - Repository - Viewmodal - LiveData - Databinding(UI) )
            viewModel.getWeather()

            //here weather is our live data which observe the changes
            viewModel.weather.observe(this, Observer {
                    weather ->
                R.layout.activity_weather.also {
                    binding.viewmodel = weather
                    Log.i("weatherresponse2",weather.main.temp.toString())
                }
            })

        }





    }

    private fun checkConnectivity():Boolean{
        var cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activityNetwork = cm.activeNetworkInfo
        val isConnected = activityNetwork != null && activityNetwork.isConnectedOrConnecting

        if(!isConnected){
            Toast.makeText(this,"Check network connection",Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}
