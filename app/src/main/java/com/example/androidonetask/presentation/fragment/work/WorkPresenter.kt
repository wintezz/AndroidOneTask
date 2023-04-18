package com.example.androidonetask.presentation.fragment.work

import androidx.lifecycle.MutableLiveData
import com.example.androidonetask.data.repository.Repository
import com.example.androidonetask.data.retrofit.NetworkState
import com.example.androidonetask.presentation.utils.TrackMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class WorkPresenter(
    private var mainView: WorkContract.View?,
    private val repository: Repository
) : WorkContract.Presenter {

    private val networkState = MutableLiveData<NetworkState>()
    private var disposable: Disposable? = null

    override fun loadTracks() {
        disposable =
            repository
                .getTracks()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    val list = TrackMapper.buildFrom(data)
                    networkState.postValue(NetworkState.LOADED)
                    mainView?.showContent(list)
                }, {
                    networkState.postValue(NetworkState.ERROR)
                    mainView?.showError()
                })
    }

    override fun onDestroyView() {
        disposable?.dispose()
        disposable = null
        mainView = null
    }
}