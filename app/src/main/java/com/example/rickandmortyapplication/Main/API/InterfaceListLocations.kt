package com.example.rickandmortyapplication.Main.API

import com.example.rickandmortyapplication.Main.Model.Locations.ContentLocations
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface InterfaceListLocations {
    //récupère la première page de l'application
    @Headers("Content-Type: application/json")
    @GET("location/")
    suspend fun getJsonLocation(): ContentLocations

    //récupère les différentes données contenu dans les pages en fonction de la valeur en paramètre
    @Headers("Content-Type: application/json")
    @GET("location")
    suspend fun getPageLocation(@Query("page") page: Int): ContentLocations
}