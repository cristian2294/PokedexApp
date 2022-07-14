package com.example.pokedexapp.data.model

import com.google.gson.annotations.SerializedName


data class PokeResponse(

    @SerializedName("results")
    var results: ArrayList<Result>
)

data class Result(

    @SerializedName("name")
    var name: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("id")
    var id: Int
)

data class Pokemon(

    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("height")
    var height: Int,
    @SerializedName("weight")
    var weight: Int,
    @SerializedName("sprites")
    var sprites: Sprite,
    @SerializedName("types")
    var types: List<PokeType>
)

data class Sprite(
    @SerializedName("front_default")
    var front_default: String?,
    @SerializedName("front_shiny")
    var front_shiny: String?,
    @SerializedName("other")
    var other: Other

    )

data class Other (
    @SerializedName("official-artwork")
    var officialArtwork: OfficialArtwork
        )

data class  OfficialArtwork(
    @SerializedName("front_default")
    var front_default: String
)

data class PokeType(
    @SerializedName("slot")
    var slot: Int,
    @SerializedName("type")
    var type: Type
)

data class Type(
    @SerializedName("name")
    var name: String?,
    @SerializedName("url")
    var url: String
)



