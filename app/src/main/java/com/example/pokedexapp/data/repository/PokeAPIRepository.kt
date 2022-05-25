package com.example.pokedexapp.data.repository

import com.example.pokedexapp.data.PokeResponse
import com.example.pokedexapp.data.Pokemon
import com.example.pokedexapp.data.network.PokeService


class PokeAPIRepository() {

    private val api =  PokeService()

    suspend fun getAllPokemons(limit: Int, offset:Int): PokeResponse {
        val pokeResponse = api.getPokemons(limit, offset)
        return pokeResponse
    }

    suspend fun getDetailPokemon(name: String): Pokemon {
        val pokeResponse = api.getDetailPokemon(name)
        return pokeResponse
    }
}