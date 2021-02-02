package com.example.rickandmortyapplication.Main.ViewModel

import androidx.lifecycle.*
import com.example.rickandmortyapplication.Main.Database.GlobalRepository
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.Main.Model.Favory.Favory
import kotlinx.coroutines.launch

class FavoryViewModel(private val repository: GlobalRepository): ViewModel() {
    private var listFavory = MutableLiveData<ArrayList<Favory>>()

    fun getFavory(): LiveData<ArrayList<Favory>>?{
        viewModelScope.launch {
            listFavory.postValue(repository.getFavory() as ArrayList<Favory>)
        }
        return listFavory
    }

    fun insert(character:Character){
        viewModelScope.launch {
            val favory = Favory(character.id, character.nameCharacter, character.status, character.species, character.type, character.gender, character.origin, character.location, character.image,character.episode, character.url, character.created)
            repository.insert(favory)
        }
    }
}
class RoomViewModelFactoryFavory(private val repository: GlobalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}