package com.example.showtracker.model

import com.google.gson.annotations.SerializedName

data class AnimeDetailModel(
    @SerializedName("title")
    val title: String?,
    @SerializedName("synopsis")
    val summary: String?,
    @SerializedName("image_url")
    val image_url: String?,
    @SerializedName("mal_id")
    val id: Int,
    @SerializedName("genres")
    val genres:List<Genre>
)

data class Genre(
    @SerializedName("name")
    val title: String
)