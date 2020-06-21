package com.example.rickandmorty.VO.Episode

import com.google.gson.annotations.SerializedName

class EpisodeVO {
    var id : Int? = null
    var name : String? = null
    var episode : String? = null
    var characters : List<String?>? = null
    var url : String? = null
    var created : String? = null
    @SerializedName("air_date")
    var airDate : String? = null
}