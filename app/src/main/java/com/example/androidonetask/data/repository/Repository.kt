package com.example.androidonetask.data.repository

import com.example.androidonetask.data.model.TrackListResponse
import com.example.androidonetask.data.retrofit.AppState
import io.reactivex.Observable

interface Repository {
    fun getTracks(): Observable<AppState<TrackListResponse>>
}