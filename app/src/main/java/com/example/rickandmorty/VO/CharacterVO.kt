package com.example.rickandmorty.VO

class CharacterVO {
    var id : Int? = null
    var name : String? = null
    var status : String? = null
    var species : String? = null
    var type : String? = null
    var gender : String? = null
    var origin : CharacterOriginVO? = null
    var location : CharacterLocationVO? = null
    var image : String? = null
    var episode : List<String?>? = null
    var url : String? = null
    var created : String? = null
}

class CharacterLocationVO {
    var name : String? = null
    var url : String? = null
}

class CharacterOriginVO {
    var name : String? = null
    var url : String? = null
}