package com.example.pokedexapp.domain

import com.example.pokedexapp.data.model.Pokemon
import com.example.pokedexapp.data.repository.PokeAPIRepository
import javax.inject.Inject

class GetDetailPokemonUseCase @Inject constructor(private val repository: PokeAPIRepository) {

    suspend operator fun invoke(name:String): Pokemon = repository.getDetailPokemon(name)
}