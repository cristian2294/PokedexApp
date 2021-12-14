package com.example.pokedexapp.data.network

import com.example.pokedexapp.core.RetrofitHelper
import com.example.pokedexapp.data.PokeResponse
import com.example.pokedexapp.data.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class PokeService {
    
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getPokemons(limit: Int, offset: Int): PokeResponse{
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PokeApiClient::class.java).getAllPokemons(limit, offset)
            response.body()!!
        }
    }

    suspend fun getDetailPokemon(namePokemon: String): Pokemon{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(PokeApiClient::class.java).getDetailPokemon(namePokemon)
            response.body()!!
        }
    }

}