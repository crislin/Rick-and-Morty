package com.example.rickandmorty.VO

class ResultEpisodeVO {
    var info : InfoEpisodeVO? = null
    var results : List<EpisodeVO?>? = null
}

class InfoEpisodeVO{
    var count : Int? = null
    var pages : Int? = null
    var next : String? = null
    var prev : String? = null
}