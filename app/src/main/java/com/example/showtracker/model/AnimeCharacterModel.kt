package com.example.showtracker.model

import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("image_url")
    val image_url: String?,
    @SerializedName("name")
    val name: String?
)

data class AnimeCharacterModel(
    @SerializedName("characters")
    val characterArray:Characters
)