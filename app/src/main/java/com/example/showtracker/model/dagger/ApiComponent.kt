package com.example.showtracker.model.dagger

import com.example.showtracker.model.AnimeService
import com.example.showtracker.viewmodel.AnimeListViewModel
import dagger.Component

@Component(modules = [AnimeServiceModule::class])
interface ApiComponent{
    fun inject(service: AnimeService)
    fun give(viewModel:AnimeListViewModel)
    fun getAnimeService():AnimeService
}