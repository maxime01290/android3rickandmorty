package com.example.rickandmortyapplication.Main.Model.Character

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Location (
    @SerializedName("name")
    @Expose
    var nameLocation: String? = null,

    @SerializedName("url")
    @Expose
    var urlLocation: String? = null

):Serializable