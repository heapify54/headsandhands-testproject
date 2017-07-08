package ru.mobiledev.headsandhands_testproj.data.remote

import com.google.gson.reflect.TypeToken
import rx.Single
import rx.SingleSubscriber
import rx.Subscription

/**
 * Created by heapify on 07.07.2017.
 */
object NetworkConst {
    const val TAG = "NetworkConst"

    val BASE_URL: String
        get() = RELEASE_BASE_URL

    private const val RELEASE_BASE_URL = "http://openwheathermap.com"
}

@Suppress("UNCHECKED_CAST")
fun <T> responseTransformer() = transformer as Single.Transformer<BaseResponse<T>, T>

private val transformer = Single.Transformer<BaseResponse<Any>, Any> { source: Single<BaseResponse<Any>> ->
    source.flatMap {
        val error = it.error
        if (error != null) {
            Single.error<BaseResponse<*>>(it.error)
        } else {
            Single.just(it.data)
        }
    }
}

inline fun <reified T> genericType() = object : TypeToken<T>() {}.type!!

inline fun <T> Single<T>.subscribeNetwork(crossinline onSuccess: (T?) -> Unit,
                                          crossinline onError: (NetworkError) -> Unit
): Subscription = subscribe(object : SingleSubscriber<T?>() {
    override fun onSuccess(value: T?) {
        onSuccess(value)
    }

    override fun onError(error: Throwable) {
        if (error is NetworkError) {
            onError(error)
        } else {
            onError(error)
        }
    }
})