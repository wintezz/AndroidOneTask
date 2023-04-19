package com.example.androidonetask.presentation.fragment.work

import com.example.androidonetask.data.repository.Repository
import com.example.androidonetask.data.retrofit.AppState
import com.example.androidonetask.presentation.utils.TrackMapper
import io.reactivex.disposables.CompositeDisposable

class WorkPresenter(
    private var mainView: WorkContract.View?,
    private val repository: Repository
) : WorkContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun loadTracks() {
        disposable.add(
            repository.getTracks()
                .subscribe({ response ->
                    if (response is AppState.Success) {
                        val data = response.data
                        val list = TrackMapper.buildFrom(data)
                        mainView?.showContent(list)
                    }
                }, {
                    mainView?.showError()
                })
        )
    }

    override fun onDestroyView() {
        mainView = null
        disposable.dispose()
    }
}