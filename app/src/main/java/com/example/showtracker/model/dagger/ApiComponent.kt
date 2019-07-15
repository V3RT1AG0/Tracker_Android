package com.example.showtracker.model.dagger

import com.example.showtracker.model.AnimeService
import com.example.showtracker.model.room.AnimeDAO
import com.example.showtracker.viewmodel.AnimeListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AnimeServiceModule::class,RoomDBModule::class])
interface ApiComponent{
//    fun inject(service: AnimeService)
//    fun give(viewModel:AnimeListViewModel)
    fun getAnimeDAO():AnimeDAO
    fun getAnimeService():AnimeService
}