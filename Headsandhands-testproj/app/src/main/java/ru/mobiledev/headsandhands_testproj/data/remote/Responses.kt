package ru.mobiledev.headsandhands_testproj.data.remote

/**
 * Created by heapify on 07.07.2017.
 */
class BaseResponse<out T>(val main: T?,
                          val error: NetworkError?)

class WeatherResponse(val temp: Double?)