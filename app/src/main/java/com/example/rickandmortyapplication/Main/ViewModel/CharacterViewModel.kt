package com.example.rickandmortyapplication.Main.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.Main.Repository.CharactersRepository
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    private var listCharacter: LiveData<ArrayList<Character>>? = null

    private val charactersRepository:CharactersRepository = CharactersRepository()

    fun getCharactersList():LiveData<ArrayList<Character>>?{
        viewModelScope.launch {
            listCharacter = charactersRepository.getRepositoryCharactersList()
        }
        return listCharacter
    }

    fun getUpdateCharactersList(numPage:Int):LiveData<ArrayList<Character>>?{
        viewModelScope.launch {
            listCharacter = charactersRepository.getUpdateRepositoryCharactersList(numPage)
        }
        return listCharacter
    }

}


