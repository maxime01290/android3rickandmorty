package com.example.rickandmortyapplication.Main.Network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortyapplication.Main.API.InterfaceListCharacters
import com.example.rickandmortyapplication.Main.API.InterfaceListEpisodes
import com.example.rickandmortyapplication.Main.API.InterfaceListLocations
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.Main.Model.Character.ContentCharacter
import com.example.rickandmortyapplication.Main.Model.Episodes.ContentEpisodes
import com.example.rickandmortyapplication.Main.Model.Episodes.Episodes
import com.example.rickandmortyapplication.Main.Model.Locations.ContentLocations
import com.example.rickandmortyapplication.Main.Model.Locations.Locations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NetworkManager {

    //region CHARACTERS
    val RETROFIT_CHARACTERS: InterfaceListCharacters by lazy {
        RetrofitSingleton.getRetrofitInstance().create(InterfaceListCharacters::class.java)
    }

    suspend fun getNetworkListePageCharactersCoroutine(): ArrayList<Character>? {
        return RETROFIT_CHARACTERS.getJsonCharacters().characters
    }

    suspend fun getUpdateNetworkPageCharacters(numPage: Int):ArrayList<Character>?{
        return RETROFIT_CHARACTERS.getPageCharacters(numPage).characters
    }

    //endregion

    //region EPISODES
    val RETROFIT_EPISODES: InterfaceListEpisodes by lazy {
        RetrofitSingleton.getRetrofitInstance().create(InterfaceListEpisodes::class.java)
    }

    suspend fun getNetworkListePageEpisodes():ArrayList<Episodes>?{
        return RETROFIT_EPISODES.getJsonEpisodes().results
    }

    suspend fun getUpdateNetworkPageEpisodes(numPage: Int):ArrayList<Episodes>?{
        return RETROFIT_EPISODES.getPageEpisode(numPage).results
    }
    //endregion

    //region LOCATIONS
    val RETROFIT_LOCATIONS: InterfaceListLocations by lazy {
        RetrofitSingleton.getRetrofitInstance().create(InterfaceListLocations::class.java)
    }

    suspend fun getNetworkListPageLocations():ArrayList<Locations>?{
        return RETROFIT_LOCATIONS.getJsonLocation().locations
    }

    suspend fun getUpdateNetworkPageLocations(numPage: Int):ArrayList<Locations>?{
        return RETROFIT_LOCATIONS.getPageLocation(numPage).locations
    }

//    val CONTENT_LOCATIONS : Call<ContentLocations?>? by lazy{
//        RETROFIT_LOCATIONS.getJsonLocation()
//    }
//
//    val mutableListLocations = MutableLiveData<ArrayList<Locations>?>()
//
//    fun getNetworkPageLocations(numPage:Int): Call<ContentLocations?>? {
//        return RETROFIT_LOCATIONS.getPageLocation(numPage)
//    }
//
//    fun getNetworkListePageLocations():MutableLiveData<ArrayList<Locations>?>{
//        CONTENT_LOCATIONS?.clone()?.enqueue(object : Callback<ContentLocations?> {
//            override fun onResponse(
//                    call: Call<ContentLocations?>,
//                    response: Response<ContentLocations?>
//            ) {
//                mutableListLocations.postValue(response.body()?.locations)
//            }
//            override fun onFailure(call: Call<ContentLocations?>, t: Throwable) {
//                Log.d("tag", "OnFailure")
//            }
//        })
//        return mutableListLocations
//    }
//
//    fun updateNetworkPageLocations(numPage:Int):MutableLiveData<ArrayList<Locations>?>{
//        getNetworkPageLocations(numPage)?.enqueue(object : Callback<ContentLocations?> {
//            override fun onFailure(call: Call<ContentLocations?>, t: Throwable) {
//
//            }
//            override fun onResponse(call: Call<ContentLocations?>, response: Response<ContentLocations?>) {
//                mutableListLocations.postValue(response.body()?.locations)
//            }
//        })
//        return mutableListLocations
//    }
    //endregion
}