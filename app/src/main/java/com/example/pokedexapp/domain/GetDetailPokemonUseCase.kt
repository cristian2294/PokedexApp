package com.example.pokedexapp.domain

import com.example.pokedexapp.data.Pokemon
import com.example.pokedexapp.data.repository.PokeAPIRepository

class GetDetailPokemonUseCase {

    private val respository = PokeAPIRepository()

    suspend operator fun invoke(name:String): Pokemon = respository.getDetailPokemon(name)
}