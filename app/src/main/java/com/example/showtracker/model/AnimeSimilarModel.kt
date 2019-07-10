package com.example.showtracker.model

import com.google.gson.annotations.SerializedName

data class Recommendations(
    @SerializedName("image_url")
    override val image_url: String?,
    @SerializedName("title")
    override val title: String?
):RosterInterface

data class AnimeSimilarModel(
    @SerializedName("recommendations")
    val recommendationsArray:List<Recommendations>
)