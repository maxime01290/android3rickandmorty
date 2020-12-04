package com.example.rickandmortyapplication.Main.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.Main.Network.NetworkManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CharactersRepository {
    private val list = MutableLiveData<ArrayList<Character>>()

    suspend fun getRepositoryCharactersList():LiveData<ArrayList<Character>>{
        GlobalScope.launch {
            list.postValue(NetworkManager.getNetworkListePageCharactersCoroutine())
        }

        return list
    }

    suspend fun getUpdateRepositoryCharactersList(numPage:Int):LiveData<ArrayList<Character>>{
        GlobalScope.launch {
            list.postValue(NetworkManager.getUpdateNetworkPageCharacters(numPage))
        }
        return list
    }
}