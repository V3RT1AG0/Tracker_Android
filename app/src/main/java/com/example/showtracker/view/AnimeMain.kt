package com.example.showtracker.view



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.showtracker.R
import com.example.showtracker.model.Anime
import com.example.showtracker.model.Characters
import com.example.showtracker.model.Recommendations
import com.example.showtracker.viewmodel.AnimeMainViewModel
import kotlinx.android.synthetic.main.anime_main_layout.*

class AnimeMain : AppCompatActivity() {

    lateinit var viewModelProvider: AnimeMainViewModel
    lateinit var charactersAdapter:AnimeRosterAdapter<Characters>
    lateinit var similarAdapter:AnimeRosterAdapter<Recommendations>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.anime_main_layout)
//           val id = intent.extras.getInt("id")
        val anime = intent.extras.getSerializable("anime") as Anime

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

        viewModelProvider.getAnimeFromLocalDb()
        followButton.setOnClickListener{v->
            viewModelProvider.storeAnimeInLocalDb(anime)
        }

        setObservers()
        viewModelProvider.fetchDataFor(anime.id)
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