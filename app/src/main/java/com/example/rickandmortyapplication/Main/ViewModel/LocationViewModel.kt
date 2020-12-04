package com.example.rickandmortyapplication.Main.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapplication.Main.Model.Episodes.Episodes
import com.example.rickandmortyapplication.Main.Model.Locations.Locations
import com.example.rickandmortyapplication.Main.Network.NetworkManager
import com.example.rickandmortyapplication.Main.Repository.EpisodeRepository
import com.example.rickandmortyapplication.Main.Repository.LocationsRepository
import kotlinx.coroutines.launch

class LocationViewModel : ViewModel() {

    private lateinit var locationsList: LiveData<ArrayList<Locations>>

    private val locationsRepository: LocationsRepository = LocationsRepository()

    fun getLocationsList(): LiveData<ArrayList<Locations>> {
        viewModelScope.launch {
            locationsList = locationsRepository.getRepositoryLocationsList()
        }
        return locationsList
    }

    fun getUpdateLocationsList(numPage:Int): LiveData<ArrayList<Locations>> {
        viewModelScope.launch {
            locationsList = locationsRepository.getUpdateRepositoryEpisodesList(numPage)
        }
        return locationsList
    }
}