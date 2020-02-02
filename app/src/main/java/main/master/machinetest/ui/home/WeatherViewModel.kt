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
    val weather : LiveData<Weather1>
        get() = _weather

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