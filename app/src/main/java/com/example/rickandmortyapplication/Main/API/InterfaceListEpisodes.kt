package com.example.rickandmortyapplication.Main.API

import com.example.rickandmortyapplication.Main.Model.Character.ContentCharacter
import com.example.rickandmortyapplication.Main.Model.Episodes.ContentEpisodes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface InterfaceListEpisodes {
    //récupère la première page de l'application
    @Headers("Content-Type: application/json")
    @GET("episode/")
    suspend fun getJsonEpisodes(): ContentEpisodes

    //récupère les différentes données contenu dans les pages en fonction de la valeur en paramètre
    @Headers("Content-Type: application/json")
    @GET("episode")
    suspend fun getPageEpisode(@Query("page") page: Int): ContentEpisodes
}