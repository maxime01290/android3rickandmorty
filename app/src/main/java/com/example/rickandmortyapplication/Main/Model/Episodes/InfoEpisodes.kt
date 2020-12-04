package com.example.rickandmortyapplication.Main.Model.Episodes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class InfoEpisodes (
    @SerializedName("count")
    @Expose
    var count: Int? = null,

    @SerializedName("pages")
    @Expose
    var pages: Int? = null,

    @SerializedName("next")
    @Expose
    var next: String? = null,

    @SerializedName("prev")
    @Expose
    var prev: Any? = null

)