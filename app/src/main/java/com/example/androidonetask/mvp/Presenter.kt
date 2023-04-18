package com.example.androidonetask.mvp

import com.example.androidonetask.data.repository.Repository
import com.example.androidonetask.presentation.utils.TrackMapper
import io.reactivex.disposables.Disposable

class Presenter(
    private var mainView: Contract.View?
) : Contract.Presenter, Contract.Model {

    private var disposable: Disposable? = null

    override fun handleApi() {
        disposable = Repository.getTracks().subscribe({ data ->
            val list = TrackMapper.buildFrom(data)
            mainView?.showContent(list)
        }, { throwable ->
            throwable.printStackTrace()
        })
    }

    override fun onDestroyView() {
        disposable?.dispose()
        disposable = null
        mainView = null
    }
}