package com.example.rickandmortyapplication.Main.Model.Converters

import androidx.room.TypeConverter
import com.example.rickandmortyapplication.Main.Model.Character.Origin
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ConvertOrigin {

    @TypeConverter
    fun StringToOrigin(value: String?): Origin? {
        val gson = Gson()
        val type: Type? = object : TypeToken<Origin?>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun originToString(origin: Origin?): String? {
        val gson = Gson()
        return gson.toJson(origin)
    }
}