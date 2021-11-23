package com.example.pokedexapp.data

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
    var sprites: Sprite

)

data class Sprite(
    @SerializedName("front_default")
    var front_default: String?,
    @SerializedName("front_shiny")
    var front_shiny: String?
    )



