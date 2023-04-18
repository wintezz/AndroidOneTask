package com.example.androidonetask.mvp.news

class NewsPresenter(
    private var newsView: NewsContract.View?
) : NewsContract.Presenter {
}