package com.example.showtracker.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "AnimeData")
data class Anime(
    @ColumnInfo(name = "title")  //This annotation is for Room
    @SerializedName("title")  //This annotation is for Retrofit
    val title: String?,
    @ColumnInfo(name = "summary")
    @SerializedName("synopsis")
    val summary: String?,
    @ColumnInfo(name = "image_url")
    @SerializedName("image_url")
    val image_url: String?,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("mal_id")
    val id: Int
):Serializable

data class SeasonalData(
    @SerializedName("anime")
    val anime: List<Anime>
)

