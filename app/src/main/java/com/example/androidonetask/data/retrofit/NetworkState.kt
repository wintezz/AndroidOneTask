package com.example.androidonetask.data.retrofit

enum class Status {
    SUCCESS,
    FAILED
}

class NetworkState(val status: Status, val msg: String) {
    companion object {
        val LOADED: NetworkState = NetworkState(Status.SUCCESS, "Success")
        val ERROR: NetworkState = NetworkState(Status.FAILED, "Error")
    }
}