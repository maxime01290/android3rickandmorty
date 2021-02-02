package com.example.rickandmortyapplication.Main.Model.Locations

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmortyapplication.Main.Model.BaseClass
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Locations")
data class Locations (
    @SerializedName("id")
    @Expose
    @PrimaryKey
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("type")
    @Expose
    var type: String? = null,

    @SerializedName("dimension")
    @Expose
    var dimension: String? = null,

    @SerializedName("residents")
    @Expose
    var residents: List<String?>? = null,

    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("created")
    @Expose
    var created: String? = null
):Serializable,BaseClass()