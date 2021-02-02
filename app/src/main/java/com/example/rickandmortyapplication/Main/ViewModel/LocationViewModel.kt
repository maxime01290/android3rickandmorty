package com.example.rickandmortyapplication.Main.ViewModel

import androidx.lifecycle.*
import com.example.rickandmortyapplication.Main.Database.GlobalRepository
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.Main.Model.Episodes.Episodes
import com.example.rickandmortyapplication.Main.Model.Favory.Favory
import com.example.rickandmortyapplication.Main.Model.Locations.Locations
import com.example.rickandmortyapplication.Main.Network.NetworkManager
import com.example.rickandmortyapplication.Main.Repository.EpisodeRepository
import com.example.rickandmortyapplication.Main.Repository.LocationsRepository
import kotlinx.coroutines.launch

class LocationViewModel(private val repository: GlobalRepository) : ViewModel() {

    private var locationsList = MutableLiveData<ArrayList<Locations>>()

    fun getUpdateLocationsList(numPage:Int): LiveData<ArrayList<Locations>> {
        viewModelScope.launch {
            locationsList.postValue(repository.getLocationsByPage(numPage) as ArrayList<Locations>)
        }
        return locationsList
    }

}
class RoomViewModelFactoryLocations(private val repository: GlobalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LocationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}