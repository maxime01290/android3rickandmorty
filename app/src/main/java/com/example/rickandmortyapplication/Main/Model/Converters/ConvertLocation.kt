package com.example.rickandmortyapplication.Main.Model.Converters

import androidx.room.TypeConverter
import com.example.rickandmortyapplication.Main.Model.Character.Location
import com.example.rickandmortyapplication.Main.Model.Character.Origin
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ConvertLocation {

    @TypeConverter
    fun StringToLocation(value: String?): Location? {
        val gson = Gson()
        val type: Type? = object : TypeToken<Origin?>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun locationToString(origin: Location?): String? {
        val gson = Gson()
        return gson.toJson(origin)
    }
}