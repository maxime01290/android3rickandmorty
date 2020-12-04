package com.example.rickandmortyapplication.Main.Model.Locations

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ContentLocations (
    @SerializedName("info")
    @Expose
    var infoLieux: InfoLieux? = null,

    @SerializedName("results")
    @Expose
    var locations: ArrayList<Locations>? = null

)