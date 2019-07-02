package com.example.showtracker.view

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.showtracker.R
import com.example.showtracker.loadImage
import com.example.showtracker.model.Anime
import kotlinx.android.synthetic.main.anime_list_item.view.*

/**Note **/
class AnimeListAdapter(var animeData: MutableList<Anime>) : RecyclerView.Adapter<AnimeListAdapter.myViewHolder>() {


    //Step 2: Create a adapter which extends RecyclerView Adapter which takes our viewholder as its genric type
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): myViewHolder =
        //Step3 = Create a viewholder by creating a object of myviewholder. The view is obtained by inflating the layout
        myViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.anime_list_item, parent, false))

    override fun getItemCount(): Int = animeData.size //Step 4. return size of data

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        //step 6: Specify how to display data each time the layout item is rendered
        holder.bind(animeData[position])
    }

    fun refresh(newAnimeData: List<Anime>) {
        // This doesn't work :https://stackoverflow.com/questions/37913252/kotlins-list-missing-add-remove-etc
        animeData.clear()
        animeData.addAll(newAnimeData)
        notifyDataSetChanged()
    }

    class myViewHolder(val v: View) : RecyclerView.ViewHolder(v) {


        //Step 1: Create a viewHolder which will receive the desired view
        fun bind(anime: Anime) {
            v.anime_title.text = anime.title //setText() is called ie, the setter function for text
            v.anime_summary.text = anime.summary
            v.poster.loadImage(anime.image_url)  //An extension function like this is similar to creating a subcass and implementing our own method
            v.setOnClickListener { v ->
                val intent = Intent(v.context, AnimeMain::class.java)
                intent.putExtra("id", anime.id)
                v.context.startActivity(intent)
            }
        }
    }

}