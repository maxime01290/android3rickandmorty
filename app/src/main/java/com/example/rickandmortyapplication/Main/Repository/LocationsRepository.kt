package com.example.rickandmortyapplication.Main.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortyapplication.Main.Model.Locations.Locations
import com.example.rickandmortyapplication.Main.Network.NetworkManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocationsRepository {

    private val list = MutableLiveData<ArrayList<Locations>>()

    suspend fun getRepositoryLocationsList(): LiveData<ArrayList<Locations>> {
        GlobalScope.launch {
            list.postValue(NetworkManager.getNetworkListPageLocations())
        }
        return list
    }

    suspend fun getUpdateRepositoryEpisodesList(numPage:Int): LiveData<ArrayList<Locations>> {
        GlobalScope.launch {
            list.postValue(NetworkManager.getUpdateNetworkPageLocations(numPage))
        }
        return list
    }
}