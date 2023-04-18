package com.example.androidonetask.mvp.work

import androidx.lifecycle.MutableLiveData
import com.example.androidonetask.data.repository.Repository
import com.example.androidonetask.data.retrofit.NetworkState
import com.example.androidonetask.presentation.utils.TrackMapper
import io.reactivex.disposables.Disposable

class WorkPresenter(
    private var workView: WorkContract.View?
) : WorkContract.Presenter {

    private val networkState = MutableLiveData<NetworkState>()
    private var disposable: Disposable? = null

    override fun handleApi() {
        disposable = Repository.getTracks().subscribe({ data ->
            val list = TrackMapper.buildFrom(data)
            networkState.postValue(NetworkState.LOADED)
            workView?.showContent(list)
        }, {
            networkState.postValue(NetworkState.ERROR)
            workView?.showError()
        })
    }

    override fun onDestroyView() {
        disposable?.dispose()
        disposable = null
        workView = null
    }
}