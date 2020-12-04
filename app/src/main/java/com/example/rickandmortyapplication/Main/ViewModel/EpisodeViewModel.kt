package com.example.rickandmortyapplication.Main.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.Main.Model.Episodes.Episodes
import com.example.rickandmortyapplication.Main.Network.NetworkManager
import com.example.rickandmortyapplication.Main.Repository.CharactersRepository
import com.example.rickandmortyapplication.Main.Repository.EpisodeRepository
import kotlinx.coroutines.launch

class EpisodeViewModel : ViewModel() {

    private lateinit var episodesList: LiveData<ArrayList<Episodes>>

    private val episodesRepository: EpisodeRepository = EpisodeRepository()

    fun getEpisodesList(): LiveData<ArrayList<Episodes>> {
        viewModelScope.launch {
            episodesList = episodesRepository.getRepositoryEpisodesList()
        }
        return episodesList
    }

    fun getUpdateEpisodesList(numPage:Int): LiveData<ArrayList<Episodes>> {
        viewModelScope.launch {
            episodesList = episodesRepository.getUpdateRepositoryEpisodesList(numPage)
        }
        return episodesList
    }
}