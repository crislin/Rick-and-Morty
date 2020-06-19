package com.example.rickandmorty.VO

class ResultCharacterVO {
    var info : InfoCharactersVO? = null
    var results : List<CharacterVO?>? = null
}

class InfoCharactersVO{
    var count : Int? = null
    var pages : Int? = null
    var next : String? = null
    var prev : String? = null
}