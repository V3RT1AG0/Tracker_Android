package com.example.showtracker.model

import com.google.gson.annotations.SerializedName

data class Anime(
    @SerializedName("title")
    val title: String?,
    @SerializedName("synopsis")
    val summary: String?,
    @SerializedName("image_url")
    val image_url: String?,
    @SerializedName("mal_id")
    val id: Int?
)

data class SeasonalData(
    @SerializedName("anime")
    val anime: List<Anime>
)