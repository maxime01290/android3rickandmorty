package com.example.rickandmortyapplication.Main.Model.Converters

import androidx.room.TypeConverter
import com.example.rickandmortyapplication.Main.Model.Character.Origin
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ConvertEpisode {

    @TypeConverter
    fun StringToEpisode(value: String?): List<String>? {
        val gson = Gson()
        val type: Type? = object : TypeToken<List<String>?>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun locationToString(episode: List<String>?): String? {
        val gson = Gson()
        return gson.toJson(episode)
    }
}