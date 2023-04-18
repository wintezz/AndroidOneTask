package com.example.androidonetask.presentation.fragment.work

import androidx.lifecycle.MutableLiveData
import com.example.androidonetask.data.repository.Repository
import com.example.androidonetask.data.retrofit.NetworkState
import com.example.androidonetask.presentation.utils.TrackMapper
import io.reactivex.disposables.CompositeDisposable

class WorkPresenter(
    private var mainView: WorkContract.View?,
    private val repository: Repository
) : WorkContract.Presenter {

    private val networkState = MutableLiveData<NetworkState>()
    private val disposable = CompositeDisposable()

    override fun loadTracks() {
        disposable.add(
            repository.getTracks()
                .subscribe({ data ->
                    val list = TrackMapper.buildFrom(data)
                    networkState.postValue(NetworkState.LOADED)
                    mainView?.showContent(list)
                }, {
                    networkState.postValue(NetworkState.ERROR)
                    mainView?.showError()
                })
        )
    }

    override fun onDestroyView() {
        mainView = null
        disposable.dispose()
    }
}