package ru.mobiledev.headsandhands_testproj.data.model

import ru.mobiledev.headsandhands_testproj.data.remote.WeatherResponse

/**
 * Created by heapify on 08.07.2017.
 */
fun WeatherResponse.toModel() = Weather(
        temp
)