package com.example.showtracker.model

import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("image_url")
    override val image_url: String?,
    @SerializedName("name")
    override val title: String?
):RosterInterface

data class AnimeCharacterModel(
    @SerializedName("characters")
    val characterArray:List<Characters>
)