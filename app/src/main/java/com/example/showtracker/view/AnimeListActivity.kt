package com.example.showtracker.view

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Button
import com.example.showtracker.R
import com.example.showtracker.model.Anime
import com.example.showtracker.viewmodel.AnimeListViewModel
import kotlinx.android.synthetic.main.anime_list_activity_layout.*

class AnimeListActivity : AppCompatActivity() {

    private val mylist = mutableListOf<Anime>()
    private val animeListAdapter = AnimeListAdapter(mylist)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.anime_list_activity_layout)

        val recyclerView = recyclerView
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = animeListAdapter
        }

        val viewModelProvider = ViewModelProviders.of(this).get(AnimeListViewModel::class.java)
        viewModelProvider.refresh()

        swipeRefreshLayout.setOnRefreshListener { ->
            viewModelProvider.refresh()
            swipeRefreshLayout.isRefreshing = false
         }

//        var games = mutableListOf<Anime>()
//        var gamers = listOf<Anime>()
//        games.addAll(gamers) #this works
//
//        val viewModelProvider = ViewModelProviders.of(this).get(AnimeListViewModel::class.java)
//        viewModelProvider.animes.observe(this,Observer{ animes ->
//            games.addAll(animes) #this does not
//            animeListAdapter.notifyDataSetChanged()
//        })



        viewModelProvider.animes.observe(this, Observer { animes ->
            animes?.let {
                animeListAdapter.refresh(it)
                recyclerView.visibility = View.VISIBLE
            }
        })

        viewModelProvider.loading.observe(this, Observer { loading ->
            loading?.let { progressBar.visibility = if (it) View.VISIBLE else View.GONE }
        })

        viewModelProvider.error.observe(this, Observer { errorOccoured ->
            errorOccoured?.let {
                error_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    recyclerView.visibility = View.GONE
                    error_view.visibility = View.GONE
                }
            }
        })


//        viewModelProvider.animes.observe(this,)
//        val d = Thread(Runnable { println("sadasd") })
//
//        val t:Thread = Thread({()-> print("dsf")})
//
//
//
//        val anime_recycler = recyclerView
//        var onClickListener:View.OnClickListener = {v->print("hello")}
//        anime_recycler.setOnClickListener({v-> print("hello")})
//        val runnable:Runnable = { () -> print("hello") }
//
//
//        anime_recycler.adapter = AnimeListAdapter()
//
//        viewModelProvider.animes.observe(this,)
//        val l = View.OnClickListener { println("asdas") }
//        val listener:(View)->Unit= {v->
//            print("hello")
//            print("asd")
//        }
//
//
//        val v = View(this)
//        v.setOnClickListener(listener)
//        v.setOnClickListener { x -> println("asdas") }
//
//
//        val vm = ViewModelProviders.of(this).get(AnimeListViewModel::class.java)
//        vm.animes.observe(this, { anime -> println(anime) })
//        vm.animes.observe({lifecycle}, { anime -> println(anime) })
//        vm.animes.obs
//        vm.animes.observe
//        val x = View(this)
//        x.setOnClickListener { view -> println(view) }
    }
}

