package com.example.androidonetask.di

import com.example.androidonetask.presentation.fragment.artist.ArtistFragment
import com.example.androidonetask.presentation.fragment.detail.DetailFragment
import com.example.androidonetask.presentation.fragment.expositions.ExpositionsFragment
import com.example.androidonetask.presentation.fragment.info.InfoFragment
import com.example.androidonetask.presentation.fragment.news.NewsFragment
import com.example.androidonetask.presentation.fragment.post.PostFragment
import com.example.androidonetask.presentation.fragment.work.WorkFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class])
interface ApplicationComponent {

    fun inject(workFragment: WorkFragment)

    fun inject(artistFragment: ArtistFragment)

    fun inject(detailFragment: DetailFragment)

    fun inject(expositionsFragment: ExpositionsFragment)

    fun inject(infoFragment: InfoFragment)

    fun inject(newsFragment: NewsFragment)

    fun inject(postFragment: PostFragment)
}