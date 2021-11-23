package com.example.pokedexapp.domain

import com.example.pokedexapp.data.PokeResponse
import com.example.pokedexapp.data.Pokemon
import com.example.pokedexapp.data.repository.PokeRepository

class GetDetailPokemonUseCase {

    private val respository = PokeRepository()

    suspend operator fun invoke(name:String): Pokemon = respository.getDetailPokemon(name)
}