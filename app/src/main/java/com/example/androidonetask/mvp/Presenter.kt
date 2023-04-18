package com.example.androidonetask.mvp

import androidx.lifecycle.MutableLiveData
import com.example.androidonetask.data.repository.Repository
import com.example.androidonetask.data.retrofit.NetworkState
import com.example.androidonetask.presentation.utils.TrackMapper
import io.reactivex.disposables.Disposable

class Presenter(
    private var mainView: Contract.View
) : Contract.Presenter, Contract.Model {

    private val networkState = MutableLiveData<NetworkState>()
    private var disposable: Disposable? = null

    override fun handleApi() {
        disposable = Repository.getTracks().subscribe({ data ->
            val list = TrackMapper.buildFrom(data)
            networkState.postValue(NetworkState.LOADED)
            mainView.showContent(list)
        }, {
            networkState.postValue(NetworkState.ERROR)
            mainView.showError()
        })
    }

    override fun onDestroyView() {
        disposable?.dispose()
    }
}