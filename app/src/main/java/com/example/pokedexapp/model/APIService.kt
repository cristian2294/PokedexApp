package com.example.pokedexapp.model

import com.example.pokedexapp.PokeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("pokemon?")
    fun getAllPokemons(@Query("limit") limit:Int,
                       @Query("offset")offset: Int): Call<PokeResponse>
}