package com.example.showtracker.model

import com.google.gson.annotations.SerializedName

data class Recommendations(
    @SerializedName("image_url")
    val image_url: String?,
    @SerializedName("title")
    val title: String?
)

data class AnimeSimilarModel(
    @SerializedName("recommendations")
    val recommendationsArray:Recommendations
)