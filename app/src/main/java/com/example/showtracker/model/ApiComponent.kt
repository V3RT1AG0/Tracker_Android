package com.example.showtracker.model

import com.example.showtracker.viewmodel.AnimeListViewModel
import dagger.Component

@Component(modules = [AnimeServiceModule::class])
interface ApiComponent{
    fun inject(service:AnimeService)
    fun give(viewModel:AnimeListViewModel)
}