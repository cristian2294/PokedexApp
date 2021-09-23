package com.example.pokedexapp.data.network

import com.example.pokedexapp.data.PokeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApiClient {


    @GET("pokemon?")
     suspend fun getAllPokemons(@Query("limit") limit:Int,
                               @Query("offset")offset: Int): Response<PokeResponse>


}