package com.example.pokedexapp.data.repository

import com.example.pokedexapp.data.model.PokeResponse
import com.example.pokedexapp.data.model.Pokemon
import com.example.pokedexapp.data.network.PokeService
import javax.inject.Inject


class PokeAPIRepository @Inject constructor(private val api: PokeService) {

    suspend fun getAllPokemons(limit: Int, offset:Int): PokeResponse {
        val pokeResponse = api.getPokemons(limit, offset)
        return pokeResponse
    }

    suspend fun getDetailPokemon(name: String): Pokemon {
        val pokeResponse = api.getDetailPokemon(name)
        return pokeResponse
    }
}