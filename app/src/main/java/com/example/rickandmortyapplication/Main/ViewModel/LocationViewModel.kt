package com.example.rickandmortyapplication.Main.ViewModel

import androidx.lifecycle.*
import com.example.rickandmortyapplication.Main.Database.GlobalRepository
import com.example.rickandmortyapplication.Main.Model.Locations.Locations
import kotlinx.coroutines.launch

class LocationViewModel(private val repository: GlobalRepository) : ViewModel() {

    private var locationsList = MutableLiveData<ArrayList<Locations>>()

    //renvoi les données à afficher
    fun getUpdateLocationsList(numPage:Int): LiveData<ArrayList<Locations>> {
        viewModelScope.launch {
            locationsList.postValue(repository.getLocationsByPage(numPage) as ArrayList<Locations>)
        }
        return locationsList
    }
}

//classe permettant d'instancier la base de donnée
class RoomViewModelFactoryLocations(private val repository: GlobalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LocationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}