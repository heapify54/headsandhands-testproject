package ru.mobiledev.headsandhands_testproj.data.remote.services

import retrofit2.http.GET
import retrofit2.http.Query
import ru.mobiledev.headsandhands_testproj.data.remote.BaseResponse
import ru.mobiledev.headsandhands_testproj.data.remote.NetworkConst
import ru.mobiledev.headsandhands_testproj.data.remote.WeatherResponse
import rx.Single

/**
 * Created by heapify on 07.07.2017.
 */
interface WeatherService {
    @GET("weather")
    fun checkWeather(@Query("q") city: String,
                     @Query("appid") appId: String = NetworkConst.API_KEY): Single<BaseResponse<WeatherResponse>>
}