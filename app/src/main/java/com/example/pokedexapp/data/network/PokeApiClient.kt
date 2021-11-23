package com.example.pokedexapp.data.network

import com.example.pokedexapp.data.PokeResponse
import com.example.pokedexapp.data.Pokemon
import com.example.pokedexapp.data.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiClient {


    @GET("pokemon?")
     suspend fun getAllPokemons(@Query("limit") limit:Int,
                               @Query("offset")offset: Int): Response<PokeResponse>

     @GET("pokemon/{name}")
     suspend fun getDetailPokemon(@Path("name") name:String): Response<Pokemon>

}