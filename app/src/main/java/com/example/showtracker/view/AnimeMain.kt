package com.example.showtracker.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.showtracker.R
import com.example.showtracker.model.Characters
import com.example.showtracker.model.Recommendations
import com.example.showtracker.viewmodel.AnimeListViewModel
import com.example.showtracker.viewmodel.AnimeMainViewModel
import kotlinx.android.synthetic.main.anime_main_layout.*

class AnimeMain : AppCompatActivity() {

    lateinit var viewModelProvider: AnimeMainViewModel
    lateinit var charactersAdapter:AnimeRosterAdapter<Characters>
    lateinit var similarAdapter:AnimeRosterAdapter<Recommendations>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.anime_main_layout)
        val id = intent.extras.getInt("id")

        viewModelProvider = ViewModelProviders.of(this).get(AnimeMainViewModel::class.java)

        val characters_list = mutableListOf<Characters>()
        charactersAdapter = AnimeRosterAdapter(characters_list)
        rv_people.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = charactersAdapter
        }

        val similar_list = mutableListOf<Recommendations>()
        similarAdapter = AnimeRosterAdapter(similar_list)
        rv_recommendations.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = similarAdapter
        }

        setObservers()
        viewModelProvider.fetchDataFor(id)


    }

    fun setObservers(){
        viewModelProvider.loading.observe(this, Observer { t ->

        })

        viewModelProvider.error.observe(this, Observer { t ->
            Log.e("error",t.toString())
        })

        viewModelProvider.characters.observe(this, Observer { character_list ->
                character_list?.let {
                    charactersAdapter.refresh(it)
                }
        })
    }
}