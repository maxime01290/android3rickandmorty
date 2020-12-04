package com.example.rickandmortyapplication.Main.API

import androidx.lifecycle.MutableLiveData
import com.example.rickandmortyapplication.Main.Model.Character.ContentCharacter
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface InterfaceListCharacters {
    //récupère la première page de l'application
    @Headers("Content-Type: application/json")
    @GET("character/")
    suspend fun getJsonCharacters():ContentCharacter

    //récupère les différentes données contenu dans les pages en fonction de la valeur en paramètre
    @Headers("Content-Type: application/json")
    @GET("character")
    suspend fun getPageCharacters(@Query("page") page: Int):ContentCharacter
}