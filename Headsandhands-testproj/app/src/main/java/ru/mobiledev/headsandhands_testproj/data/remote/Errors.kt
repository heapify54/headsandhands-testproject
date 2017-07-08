package ru.mobiledev.headsandhands_testproj.data.remote

/**
 * Created by heapify on 07.07.2017.
 */
class Error(val message: String? = "Неизвестная ошибка")

class NetworkError(val status: String, val code: Int,
                   val error: Error
) : RuntimeException(error.message) {
    companion object ErrorCodes {
        // TODO add error codes
    }

    override val message: String?
        get() = error.message
}