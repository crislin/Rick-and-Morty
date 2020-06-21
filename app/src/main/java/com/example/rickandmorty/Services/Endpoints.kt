package com.example.rickandmorty.Services

import com.example.rickandmorty.VO.Character.CharacterVO
import com.example.rickandmorty.VO.Character.ResultCharacterVO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoints {

    @GET("character/")
    fun getCharacters() : Call<ResultCharacterVO>

    @GET("character/{id}")
    fun getCharacterDetail(@Path("id") id : Int?) : Call<CharacterVO>
}