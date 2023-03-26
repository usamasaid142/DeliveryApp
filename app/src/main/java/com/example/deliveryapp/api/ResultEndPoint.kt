package com.example.deliveryapp.api

/**
 * A generic class that holds a value with its loading status.
 *
 * Result is usually created by the Repository classes where they return
 * `LiveData<Result<T>>` to pass back the latest data to the UI with its fetch status.
 */

data class ResultEndPoint<out T>(val status: Status, val data: T?, val message: String?) {

    // response status
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): ResultEndPoint<T> {
            return ResultEndPoint(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): ResultEndPoint<T> {
            return ResultEndPoint(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): ResultEndPoint<T> {
            return ResultEndPoint(Status.LOADING, data, null)
        }
    }
}