package com.example.androidonetask.mvp

import com.example.androidonetask.presentation.adapter.MusicAdapter
import io.reactivex.disposables.Disposable

class Presenter(
    private var mainView: Contract.View?,
    private val model: Contract.Model
) : Contract.Presenter, Contract.Model {

    private var disposable: Disposable? = null
    private var adapter = MusicAdapter(
        listenerAlbumImage = {},
        listenerPosition = {}
    )

    override fun onDestroyView() {
        disposable?.dispose()
        disposable = null
        mainView = null
    }
}