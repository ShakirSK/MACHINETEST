package main.master.machinetest.data.network


import main.master.machinetest.data.model.Weather
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {

    @GET("weather")
    suspend fun getweather(@Query("q") q:String,
                           @Query("units") units:String,
                           @Query("appid") appid:String) : Response<Weather>


    companion object {
        operator fun invoke(): MyApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .build()
                .create(MyApi::class.java)
        }
    }
}