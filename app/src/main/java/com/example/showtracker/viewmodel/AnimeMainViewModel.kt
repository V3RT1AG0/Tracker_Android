package com.example.showtracker.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.showtracker.model.*
import com.example.showtracker.model.dagger.DaggerApiComponent
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AnimeMainViewModel:ViewModel(){

    var characters = MutableLiveData<List<Characters>>()
    var similar = MutableLiveData<List<Recommendations>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    val disposable = CompositeDisposable()
    var animeService = DaggerApiComponent.create().getAnimeService()

    init {
        loading.value = true
        error.value = false
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun fetchDataFor(id:Int) {
        loading.value = true
        fetchAnimeDetails(id)

    }

    fun fetchAnimeDetails(id:Int){
        disposable.addAll(animeService.getCharacters(id)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object :DisposableSingleObserver<AnimeCharacterModel>(){
                override fun onError(e: Throwable) {
                    Log.e("error",e.message)
                    error.value = true
                    loading.value= false
                }

                override fun onSuccess(t: AnimeCharacterModel) {
                    error.value = false
                    loading.value = false
                    characters.value = t.characterArray
                }

            }))
    }


}