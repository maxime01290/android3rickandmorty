package com.example.rickandmortyapplication.Main.Model.Episodes

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmortyapplication.Main.Model.BaseClass
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Episodes")
data class Episodes (
    @SerializedName("id")
    @Expose
    @PrimaryKey
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("air_date")
    @Expose
    var airDate: String? = null,

    @SerializedName("episode")
    @Expose
    var episode: String? = null,

    @SerializedName("characters")
    @Expose
    var characters: List<String>? = null,

    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("created")
    @Expose
    var created: String? = null

):Serializable, BaseClass()