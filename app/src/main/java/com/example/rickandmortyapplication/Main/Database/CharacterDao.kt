package com.example.rickandmortyapplication.Main.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.rickandmortyapplication.Main.Model.Character.Character

@Dao
interface CharacterDao {
    @Insert(onConflict = REPLACE)
    fun save(characterList:ArrayList<Character>)

    @Query("SELECT * FROM characters")
    fun getAllCharacters():LiveData<ArrayList<Character>>
}