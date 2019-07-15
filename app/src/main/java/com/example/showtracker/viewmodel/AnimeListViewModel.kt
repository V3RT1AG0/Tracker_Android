package com.example.showtracker.viewmodel


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.showtracker.model.Anime
import com.example.showtracker.model.AnimeService
import com.example.showtracker.model.dagger.DaggerApiComponent
import com.example.showtracker.model.SeasonalData
import com.example.showtracker.model.dagger.RoomDBModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AnimeListViewModel(application: Application) : AndroidViewModel(application) {
    val animes = MutableLiveData<List<Anime>>()
    var animeServie: AnimeService = DaggerApiComponent.builder().roomDBModule(RoomDBModule(application)).build().getAnimeService()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    val disposable = CompositeDisposable()

    init {
        loading.value = true
        error.value = false
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun refresh() {
        loading.value = true
        fetchAnimeList()
    }

    private fun fetchAnimeList() {
//        animeServie.getAnimeList()
        disposable.add(animeServie.getAnimeList()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object:DisposableSingleObserver<SeasonalData>(){
                override fun onSuccess(data: SeasonalData) {
                    loading.value = false
                    error.value = false
                    animes.value = data.anime
                }

                override fun onError(e: Throwable) {
                    Log.d("error",e.message)
                    loading.value = false
                    error.value = true
                }
            }))


    }
}
