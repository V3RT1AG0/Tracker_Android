package com.example.showtracker.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.showtracker.R
import com.example.showtracker.loadImage
import com.example.showtracker.model.Anime
import com.example.showtracker.model.Characters
import com.example.showtracker.model.Recommendations
import com.example.showtracker.viewmodel.AnimeMainViewModel
import kotlinx.android.synthetic.main.anime_main_layout.*


class AnimeMain : AppCompatActivity() {

    lateinit var viewModelProvider: AnimeMainViewModel
    lateinit var charactersAdapter: AnimeRosterAdapter<Characters>
    lateinit var similarAdapter: AnimeRosterAdapter<Recommendations>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.anime_main_layout)
//           val id = intent.extras.getInt("id")
        val anime = intent.extras.getSerializable("anime") as Anime

        viewModelProvider = ViewModelProviders.of(this).get(AnimeMainViewModel::class.java)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
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

        viewModelProvider.getAnimeFromLocalDb(anime)
        followButton.setOnClickListener { v ->
            //store or remove the anime from db.
            if (viewModelProvider.isAnimeInDb.value!!) {
                Toast.makeText(this, "Anime removed from db", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Anime added to db", Toast.LENGTH_SHORT).show()
            }

            viewModelProvider.storeAnimeInLocalDb(anime)
        }

        setObservers()
        viewModelProvider.fetchDataFor(anime.id)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    fun setObservers() {
        viewModelProvider.loading.observe(this, Observer { loading ->
            loading?.let {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    container.visibility = View.GONE
                }


            }
        })

        viewModelProvider.error.observe(this, Observer { error ->
            if (error) {
                container.visibility = View.GONE
                progressBar.visibility = View.GONE
            }
            Log.e("error", error.toString())
        })

        viewModelProvider.animeDetails.observe(this, Observer { anime ->
            anime?.let {
                charactersAdapter.refresh(it.characters)
                similarAdapter.refresh(it.similar)
                tv_title.text = it.details.title
                tv_summary.text = it.details.summary
                poster_block.loadImage(it.details.image_url)
                container.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        })

        viewModelProvider.isAnimeInDb.observe(this, Observer {
            if (it == true) {
                followButton.setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))

            } else {
                followButton.setBackgroundColor(resources.getColor(R.color.colorPrimary))
            }

        })
    }
}