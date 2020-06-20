package com.example.rickandmorty.Services

import com.example.rickandmorty.VO.Character.ResultCharacterVO
import retrofit2.Call
import retrofit2.http.GET

interface Endpoints {

    @GET("character/")
    fun getCharacters() : Call<ResultCharacterVO>
}