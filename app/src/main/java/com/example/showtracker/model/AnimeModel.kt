package com.example.showtracker.model

import com.google.gson.annotations.SerializedName
import io.reactivex.Single

data class Anime(
    @SerializedName("title")
    val title: String?,
    @SerializedName("synopsis")
    val summary: String?,
    @SerializedName("image_url")
    val image_url: String?
)

data class SeasonalData(
    @SerializedName("anime")
    val anime: List<Anime>
)