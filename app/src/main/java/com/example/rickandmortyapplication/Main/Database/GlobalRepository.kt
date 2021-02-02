package com.example.rickandmortyapplication.Main.Database

import androidx.annotation.WorkerThread
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.Main.Model.Episodes.Episodes
import com.example.rickandmortyapplication.Main.Model.Favory.Favory
import com.example.rickandmortyapplication.Main.Model.Locations.Locations
import com.example.rickandmortyapplication.Main.Network.NetworkManager
import kotlinx.coroutines.flow.Flow

class GlobalRepository(private val globalDao:GlobalDao,private val application: GlobalApplication,private val networkManager:NetworkManager) {

    //select where
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getCharactersByPage(position:Int):List<Character>{
        if(application.isConnected()){
            val content = networkManager.getUpdateNetworkPageCharacters(position)
            if (!globalDao.getCharacters(position).containsAll(content!!)){
                globalDao.insertCharacters(content)
            }
        }
        return globalDao.getCharacters(position)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getEpisodesByPage(position:Int):List<Episodes>{
        if(application.isConnected()){
            val content = networkManager.getUpdateNetworkPageEpisodes(position)
            if (!globalDao.getEpisodes(position).containsAll(content!!)){
                globalDao.insertEpisodes(content)
            }
        }
        return globalDao.getEpisodes(position)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getLocationsByPage(position:Int):List<Locations>{
        if(application.isConnected()){
            val content = networkManager.getUpdateNetworkPageLocations(position)
            if (!globalDao.getLocations(position).containsAll(content!!)){
                globalDao.insertLocations(content)
            }
        }
        return globalDao.getLocations(position)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getFavory():List<Favory>{
        return globalDao.getFavory()
    }

    suspend fun insert(favory: Favory){
        globalDao.insertFavory(favory)
    }
}