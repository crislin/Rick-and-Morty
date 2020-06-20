package com.example.rickandmorty.VO.Location

class ResultLocationVO {
    var info : InfoLocationVO? = null
    var results : List<LocationVO?>? = null
}

class InfoLocationVO{
    var count : Int? = null
    var pages : Int? = null
    var next : String? = null
    var prev : String? = null
}