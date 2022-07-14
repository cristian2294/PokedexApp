package com.example.pokedexapp.domain

import com.example.pokedexapp.data.repository.PokeAPIRepository
import com.example.pokedexapp.data.model.PokeResponse
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(private val repository: PokeAPIRepository) {

    suspend operator fun invoke(limit: Int, offset:Int): PokeResponse = repository.getAllPokemons(limit, offset)
}