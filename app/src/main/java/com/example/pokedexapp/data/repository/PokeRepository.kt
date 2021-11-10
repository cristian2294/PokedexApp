package com.example.pokedexapp.data.repository

import com.example.pokedexapp.data.PokeResponse
import com.example.pokedexapp.data.network.PokeService

class PokeRepository {

    private val api =  PokeService()

    suspend fun getAllPokemons(): PokeResponse {
        val pokeResponse = api.getPokemons()
        return pokeResponse
    }
}