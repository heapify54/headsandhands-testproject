package ru.mobiledev.headsandhands_testproj.data.remote

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by heapify on 07.07.2017.
 */
object RestApi {
    val BASE_URL = NetworkConst.BASE_URL

    private var retrofit: Retrofit? = null

    fun init(context: Context, authenticator: Authenticator) {
        val okHttpClient = OkHttpClient.Builder()
                .authenticator (authenticator)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient.addNetworkInterceptor(interceptor)

        val gson = GsonBuilder().create()
        retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .client(okHttpClient.build())
                .build()
    }

    fun <T> createService(serviceClass: Class<T>): T {
        if (retrofit == null) {
            throw IllegalStateException("Call `RestApi.init(Context, Authenticator)` before calling this method.")
        }
        return retrofit!!.create(serviceClass)
    }
}