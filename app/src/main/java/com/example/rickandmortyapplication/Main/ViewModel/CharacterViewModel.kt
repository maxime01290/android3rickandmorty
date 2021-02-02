package com.example.rickandmortyapplication.Main.ViewModel

import androidx.lifecycle.*
import com.example.rickandmortyapplication.Main.Database.GlobalRepository
import com.example.rickandmortyapplication.Main.Model.Character.Character
import kotlinx.coroutines.launch

class CharacterViewModel(private val repository: GlobalRepository) : ViewModel() {

    private var listCharacter = MutableLiveData<ArrayList<Character>>()

    fun getUpdateCharactersList(numPage:Int):LiveData<ArrayList<Character>>?{
        viewModelScope.launch {
            listCharacter.postValue(repository.getCharactersByPage(numPage) as ArrayList<Character>)
        }
        return listCharacter
    }

}

class RoomViewModelFactoryCharacters(private val repository: GlobalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
