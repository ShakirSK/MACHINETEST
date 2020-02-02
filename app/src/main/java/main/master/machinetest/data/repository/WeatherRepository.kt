package main.master.machinetest.data.repository

import main.master.machinetest.data.network.MyApi

class WeatherRepository(
    private val api: MyApi
) : SafeApiRequest() {

    suspend fun getWeather() = apiRequest { api.getweather("Mumbai","metric","74d6e94a0aedb2c881fc9365d711833f") }

}