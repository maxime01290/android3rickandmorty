package com.example.rickandmortyapplication.Main.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.Main.Model.Episodes.Episodes
import com.example.rickandmortyapplication.Main.Model.Favory.Favory
import com.example.rickandmortyapplication.Main.Model.Locations.Locations
import kotlinx.coroutines.flow.Flow

@Dao()
interface GlobalDao {

    ///insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(character: ArrayList<Character>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisodes(episodes: ArrayList<Episodes>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocations(locations: ArrayList<Locations>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavory(favory: Favory)

    /// select
    @Query("SELECT * FROM characters WHERE characters.id <= :position*20 and characters.id >= (:position-1) *20")
    suspend fun getCharacters(position:Int): List<Character>

    @Query("SELECT * FROM episodes WHERE episodes.id <= :position*20 and episodes.id >= (:position-1)*20")
    suspend fun getEpisodes(position:Int): List<Episodes>

    @Query("SELECT * FROM locations WHERE locations.id <= :position*20 and locations.id>= (:position-1)*20")
    suspend fun getLocations(position:Int): List<Locations>

    @Query("SELECT * FROM favory")
    suspend fun getFavory(): List<Favory>

    ///delete all
    @Query("DELETE FROM characters")
    suspend fun deleteAllCharacters()

    @Query("DELETE FROM episodes")
    suspend fun deleteAllEpisodes()

    @Query("DELETE FROM locations")
    suspend fun deleteAllLocations()

    @Query("DELETE FROM locations")
    suspend fun deleteAllFavory()
}