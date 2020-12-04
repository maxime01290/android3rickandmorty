package com.example.rickandmortyapplication.Main.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmortyapplication.Main.Model.Character.Character

@Database(entities = arrayOf(Character::class), version = 1)
abstract class CharactersDatabase :RoomDatabase(){
    abstract fun characterDao():CharacterDao
}