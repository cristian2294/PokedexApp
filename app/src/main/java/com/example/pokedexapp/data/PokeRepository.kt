package com.example.pokedexapp.data

import com.example.pokedexapp.data.network.PokeService

class PokeRepository {

    private val api =  PokeService()

    suspend fun getAllPokemons():PokeResponse{
        val response = api.getPokemons()
        return response!!
    }
}