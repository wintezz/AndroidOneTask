package com.example.androidonetask.data.retrofit

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

fun <T> Observable<Response<T>>.request(): Observable<AppState<T>> {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map { response ->
            try {
                if (response.isSuccessful) {
                    response.body()?.let {
                        AppState.Success(it)
                    } ?: AppState.Error(Throwable("Body is null"))
                } else {
                    AppState.ServerError(
                        code = response.code(),
                        json = response.errorBody()?.string().orEmpty()
                    )
                }
            } catch (e: Throwable) {
                AppState.Error(e)
            }
        }
}


