package com.example.rickandmortyapplication.Main.Network

import com.example.rickandmortyapplication.Main.API.InterfaceListCharacters
import com.example.rickandmortyapplication.Main.API.InterfaceListEpisodes
import com.example.rickandmortyapplication.Main.API.InterfaceListLocations
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.Main.Model.Episodes.Episodes
import com.example.rickandmortyapplication.Main.Model.Locations.Locations

object NetworkManager {

    //region CHARACTERS
    val RETROFIT_CHARACTERS: InterfaceListCharacters by lazy {
        RetrofitSingleton.getRetrofitInstance().create(InterfaceListCharacters::class.java)
    }

    //recupère les données de la première page
    suspend fun getNetworkListePageCharactersCoroutine(): ArrayList<Character>? {
        return RETROFIT_CHARACTERS.getJsonCharacters().characters
    }

    //recupère les données des pages mises à jours
    suspend fun getUpdateNetworkPageCharacters(numPage: Int):ArrayList<Character>?{
        return RETROFIT_CHARACTERS.getPageCharacters(numPage).characters
    }

    //endregion

    //region EPISODES
    val RETROFIT_EPISODES: InterfaceListEpisodes by lazy {
        RetrofitSingleton.getRetrofitInstance().create(InterfaceListEpisodes::class.java)
    }

    //recupère les données de la première page
    suspend fun getNetworkListePageEpisodes():ArrayList<Episodes>?{
        return RETROFIT_EPISODES.getJsonEpisodes().results
    }

    //recupère les données des pages mises à jours
    suspend fun getUpdateNetworkPageEpisodes(numPage: Int):ArrayList<Episodes>?{
        return RETROFIT_EPISODES.getPageEpisode(numPage).results
    }
    //endregion

    //region LOCATIONS
    val RETROFIT_LOCATIONS: InterfaceListLocations by lazy {
        RetrofitSingleton.getRetrofitInstance().create(InterfaceListLocations::class.java)
    }

    //recupère les données de la première page
    suspend fun getNetworkListPageLocations():ArrayList<Locations>?{
        return RETROFIT_LOCATIONS.getJsonLocation().locations
    }

    //recupère les données des pages mises à jours
    suspend fun getUpdateNetworkPageLocations(numPage: Int):ArrayList<Locations>?{
        return RETROFIT_LOCATIONS.getPageLocation(numPage).locations
    }
    //endregion
}