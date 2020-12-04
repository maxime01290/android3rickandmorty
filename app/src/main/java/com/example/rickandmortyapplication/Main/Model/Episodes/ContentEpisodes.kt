package com.example.rickandmortyapplication.Main.Model.Episodes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ContentEpisodes (
    @SerializedName("info")
    @Expose
    var infoEpisodes: InfoEpisodes? = null,

    @SerializedName("results")
    @Expose
    var results: ArrayList<Episodes>? = null

):Serializable