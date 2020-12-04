package com.example.rickandmortyapplication.Main.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.Main.Model.Episodes.Episodes
import com.example.rickandmortyapplication.Main.Network.NetworkManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EpisodeRepository {
    private val list = MutableLiveData<ArrayList<Episodes>>()

    suspend fun getRepositoryEpisodesList(): LiveData<ArrayList<Episodes>> {
        GlobalScope.launch {
            list.postValue(NetworkManager.getNetworkListePageEpisodes())
        }
        return list
    }

    suspend fun getUpdateRepositoryEpisodesList(numPage:Int): LiveData<ArrayList<Episodes>> {
        GlobalScope.launch {
            list.postValue(NetworkManager.getUpdateNetworkPageEpisodes(numPage))
        }
        return list
    }
}