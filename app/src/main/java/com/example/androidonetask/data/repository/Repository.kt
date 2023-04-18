package com.example.androidonetask.data.repository

import com.example.androidonetask.data.model.TrackListResponse
import io.reactivex.Observable

interface Repository {
    fun getTracks(): Observable<TrackListResponse>
}