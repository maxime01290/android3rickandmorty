package com.example.rickandmortyapplication.Main.Model.Character

import androidx.room.ColumnInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ContentCharacter (
    @SerializedName("info")
    @Expose
    var infoPersonnage: InfoCharacter? = null,

    @SerializedName("results")
    @Expose
    var characters: ArrayList<Character>? = null

):Serializable