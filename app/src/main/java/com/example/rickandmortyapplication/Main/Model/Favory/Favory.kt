package com.example.rickandmortyapplication.Main.Model.Favory

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.rickandmortyapplication.Main.Model.BaseClass
import com.example.rickandmortyapplication.Main.Model.Character.Location
import com.example.rickandmortyapplication.Main.Model.Character.Origin
import com.example.rickandmortyapplication.Main.Model.Converters.ConvertEpisode
import com.example.rickandmortyapplication.Main.Model.Converters.ConvertLocation
import com.example.rickandmortyapplication.Main.Model.Converters.ConvertOrigin
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Favory")
data class Favory (
    @SerializedName("id") @PrimaryKey var id: Int? = null,

    @SerializedName("name") var nameCharacter: String? = null,

    @SerializedName("status") var status: String? = null,

    @SerializedName("species") var species: String? = null,

    @SerializedName("type") var type: String? = null,

    @SerializedName("gender") var gender: String? = null,

    @SerializedName("origin") @Embedded
    @TypeConverters(ConvertOrigin::class) var origin: Origin? = null,

    @SerializedName("location") @Embedded
    @TypeConverters(ConvertLocation::class) var location: Location? = null,

    @SerializedName("image") var image: String? = null,

    @SerializedName("episode") @TypeConverters(ConvertEpisode::class) var episode: List<String>? = null,

    @SerializedName("url") var url: String? = null,

    @SerializedName("created") var created: String? = null
): BaseClass()