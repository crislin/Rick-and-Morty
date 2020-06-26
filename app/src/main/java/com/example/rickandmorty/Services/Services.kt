package com.example.rickandmorty.Services

object Services {
    private var URL_ALL_CHARACTERS = "https://rickandmortyapi.com/api/character/"
    private var URL_ALL_LOCATIONS = "https://rickandmortyapi.com/api/location/"
    private var URL_ALL_EPISODES = "https://rickandmortyapi.com/api/episode/"
    private var BASE_URL = "https://rickandmortyapi.com/api/"

    fun getAllCharacters(): String = URL_ALL_CHARACTERS
    fun getAllLocations(): String = URL_ALL_LOCATIONS
    fun getAllEpisodes(): String = URL_ALL_EPISODES
    fun getBaseUrl(): String = BASE_URL
}