package com.example.rickandmortyapplication.Main.Model.Character

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Origin (
    @SerializedName("name")
    @Expose
    var nameOrigin: String? = null,

    @SerializedName("url")
    @Expose
    var urlOrigin: String? = null

):Serializable
