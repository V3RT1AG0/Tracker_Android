package com.example.showtracker.model

data class AnimeDetails(
    var characters:List<Characters>,
    var similar:List<Recommendations>,
    var details:AnimeDetailModel
)