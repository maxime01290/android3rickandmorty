package com.example.rickandmortyapplication.Main.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.rickandmortyapplication.Main.Model.Character.Character
import com.example.rickandmortyapplication.Main.Model.Converters.ConvertEpisode
import com.example.rickandmortyapplication.Main.Model.Converters.ConvertLocation
import com.example.rickandmortyapplication.Main.Model.Converters.ConvertOrigin
import com.example.rickandmortyapplication.Main.Model.Episodes.Episodes
import com.example.rickandmortyapplication.Main.Model.Favory.Favory
import com.example.rickandmortyapplication.Main.Model.Locations.Locations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Character::class,Episodes::class, Locations::class, Favory::class), version = 1,exportSchema = false)
@TypeConverters(ConvertOrigin::class,ConvertEpisode::class,ConvertLocation::class)
abstract class GlobalRoomDatabase : RoomDatabase(){
    abstract fun globalDao(): GlobalDao

    private class GlobalDatabaseCallback(private val scope : CoroutineScope) : RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch{
                    populateDatabase(database.globalDao())
                }
            }
        }
        suspend fun populateDatabase(globalDAO: GlobalDao){
            globalDAO.deleteAllCharacters()
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: GlobalRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): GlobalRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        GlobalRoomDatabase::class.java,
                        "database"
                ).addCallback(GlobalDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }
}