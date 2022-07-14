package com.example.pokedexapp.data.network

import com.example.pokedexapp.data.model.PokeResponse
import com.example.pokedexapp.data.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class PokeService @Inject constructor(private val api: PokeApiClient) {

    suspend fun getPokemons(limit: Int, offset: Int): PokeResponse {
        return withContext(Dispatchers.IO) {
            val response = api.getAllPokemons(limit, offset)
            response.body()!!
        }
    }

    suspend fun getDetailPokemon(namePokemon: String): Pokemon {
        return withContext(Dispatchers.IO){
            val response = api.getDetailPokemon(namePokemon)
            response.body()!!
        }
    }

}