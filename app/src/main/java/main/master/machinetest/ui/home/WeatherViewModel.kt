package main.master.machinetest.ui.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import kotlinx.coroutines.Job
import main.master.machinetest.data.repository.WeatherRepository
import main.master.machinetest.utils.Coroutines
import main.master.machinetest.data.model.Weather as Weather1

class WeatherViewModel(private val repository: WeatherRepository): ViewModel() {
    private lateinit var job: Job
    private val _weather = MutableLiveData<Weather1>()
    //weather is a  LiveData data holder for Weather
    val weather : LiveData<Weather1>
        get() = _weather

    //used kotlin coroutines
    //which fetch data inside an IOthread and then we have a callback to the mainthread
    //we passed getWeather() WeatherRepository which in turns gives response
    fun getWeather() {
        job = Coroutines.ioThenMain(
            { repository.getWeather() },
            { _weather.value = it }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }


}