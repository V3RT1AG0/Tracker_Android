package com.example.showtracker.viewmodel


import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.showtracker.model.*
import com.example.showtracker.model.dagger.DaggerApiComponent
import com.example.showtracker.model.dagger.RoomDBModule
import com.example.showtracker.model.room.AnimeDAO
import com.example.showtracker.model.room.MyDatabase
import io.reactivex.CompletableObserver
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function3
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

//Note that we are extending AndroidViewModel here and not just ViewModel in order to get application context
class AnimeMainViewModel(application: Application) : AndroidViewModel(application) {

    val animeDetails = MutableLiveData<AnimeDetails>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    val disposable = CompositeDisposable()
    val daggerApiComponent = DaggerApiComponent.builder().roomDBModule(RoomDBModule(application)).build()
    var animeService = daggerApiComponent.getAnimeService()
    var dao: AnimeDAO = daggerApiComponent.getAnimeDAO()
    var isAnimeInDb = MutableLiveData<Boolean>()


    init {
//        MyDatabase.getInstance(application)?.let { dao = it.getAnimeDAO() }
        loading.value = true
        error.value = false
        isAnimeInDb.value = false
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun fetchDataFor(id: Int) {
        loading.value = true
        fetchAnimeDetails(id)

    }


    private fun fetchAnimeDetails(id: Int) {

        disposable.add(Single.zip(
            animeService.getCharacters(id),
            animeService.getAnimeDetails(id),
            animeService.getSimilar(id),
            Function3<AnimeCharacterModel, AnimeDetailModel, AnimeSimilarModel, AnimeDetails> { animeCharacter, animedetail, animeSimilar ->
                AnimeDetails(animeCharacter.characterArray,animeSimilar.recommendationsArray,animedetail)
            }
        ).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<AnimeDetails>() {
                override fun onSuccess(t: AnimeDetails) {
                    animeDetails.value = t
                }

                override fun onError(e: Throwable) {
                    Log.e("CustomError:",e.message)
                }

            }))


//        disposable.addAll(
//            animeService.getCharacters(id)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(object : DisposableSingleObserver<AnimeCharacterModel>() {
//                    override fun onError(e: Throwable) {
//                        Log.e("error", e.message)
//                        error.value = true
//                        loading.value = false
//                    }
//
//                    override fun onSuccess(t: AnimeCharacterModel) {
//                        error.value = false
//                        loading.value = false
//                        characters.value = t.characterArray
//                    }
//
//                })
//        )
    }

    fun getAnimeFromLocalDb(anime: Anime){
        disposable.add(
            dao.getAll()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Anime>>() {
                    override fun onSuccess(animeList: List<Anime>) {
                        isAnimeInDb.value = anime in animeList
                        Log.i("Room", animeList.toString())

                    }

                    override fun onError(error: Throwable) {

                    }

                })
        )

    }

    fun storeAnimeInLocalDb(anime: Anime) {
        //Completable.fromAction { MyDatabase.getInstance(context)!!.getAnimeDAO().insert(anime) }
        if(!isAnimeInDb.value!!)
        dao.insert(anime)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onError(e: Throwable) {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onComplete() {
                    Log.i("Room", "Data stored successfully")
                    getAnimeFromLocalDb(anime)
                }


            })
        else
            dao.delete(anime)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CompletableObserver {
                    override fun onError(e: Throwable) {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onComplete() {
                        Log.i("Room", "Data removed successfully")
                        getAnimeFromLocalDb(anime)
                    }


                })



    }


}