package com.example.showtracker.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.showtracker.R
import com.example.showtracker.loadImage
import com.example.showtracker.model.Characters
import com.example.showtracker.model.RosterInterface
import kotlinx.android.synthetic.main.anime_list_item.view.*
import kotlinx.android.synthetic.main.anime_list_item.view.poster
import kotlinx.android.synthetic.main.roaster_cardview.view.*
import java.util.zip.Inflater


class AnimeRosterAdapter<MyType : RosterInterface>(val list: MutableList<MyType>) :
    RecyclerView.Adapter<AnimeRosterAdapter<MyType>.RosterViewHolder>() {
    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(viewHolder: RosterViewHolder, position: Int) {
        viewHolder.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RosterViewHolder = RosterViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.roaster_cardview,
            parent,
            false
        )
    )

    fun refresh(newAnimeData: List<MyType>) {
        // This doesn't work :https://stackoverflow.com/questions/37913252/kotlins-list-missing-add-remove-etc
        list.clear()
        list.addAll(newAnimeData)
        notifyDataSetChanged()
    }

    inner class RosterViewHolder(val v: View) : RecyclerView.ViewHolder(v) {

        fun bind(item: MyType) {
            v.poster.loadImage(item.image_url)
            v.name.text = item.title
        }

    }

}
