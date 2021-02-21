package com.example.rickandmortyapplication.Main.ViewModel

import androidx.lifecycle.*
import com.example.rickandmortyapplication.Main.Database.GlobalRepository
import com.example.rickandmortyapplication.Main.Model.Episodes.Episodes
import kotlinx.coroutines.launch

class EpisodeViewModel(private val repository: GlobalRepository) : ViewModel() {

    private var episodesList = MutableLiveData<ArrayList<Episodes>>()
    //renvoi les données à afficher
    fun getUpdateEpisodesList(numPage:Int): LiveData<ArrayList<Episodes>> {
        viewModelScope.launch {
            episodesList.postValue(repository.getEpisodesByPage(numPage) as ArrayList<Episodes>)
        }
        return episodesList
    }
}
//classe permettant d'instancier la base de donnée
class RoomViewModelFactoryEpisodes(private val repository: GlobalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EpisodeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EpisodeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}