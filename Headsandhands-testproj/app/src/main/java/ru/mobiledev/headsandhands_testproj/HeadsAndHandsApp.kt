package ru.mobiledev.headsandhands_testproj

import android.app.Application
import okhttp3.Authenticator
import ru.mobiledev.headsandhands_testproj.data.remote.RestApi

/**
 * Created by heapify on 07.07.2017.
 */
class HeadsAndHandsApp : Application() {
    companion object {
        private const val TAG = "HandAndHeadsApp"

        lateinit var instance: HeadsAndHandsApp
            private set
    }

    override fun onCreate() {
        super.onCreate()

        RestApi.init(this, Authenticator { _, response ->
            // TODO add authenticator logic

            response.request().newBuilder().build()
        })
    }
}