package com.example.pokedexapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface APIService {
    @GET("pokemon?")
    fun getAllPokemons(@Query("limit") limit:Int,
                       @Query("offset")offset: Int): Call<PokeResponse>


    /*
    @GET("pokemon?limit=5&offset=0")
    fun getAllPokemons(): Call<List<Result>>

     */
}