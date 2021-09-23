package com.example.pokedexapp.data

import com.google.gson.annotations.SerializedName

data class PokeResponse(

    @SerializedName("count")
    var count:Int,
    @SerializedName("next")
    var next:String,
    @SerializedName("previous")
    var previous: String?,

    @SerializedName("results")
    var results: ArrayList<Result>
)

data class Result(

    @SerializedName("name")
    var name: String,
    @SerializedName("url")
    var url: String
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
    var images: Sprite

)

data class Sprite(
    @SerializedName("frontDefault")
    var frontDefault: String?,
    @SerializedName("frontShiny")
    var frontShiny: String?
    )