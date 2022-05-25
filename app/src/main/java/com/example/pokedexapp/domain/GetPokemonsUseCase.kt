package com.example.pokedexapp.domain

import com.example.pokedexapp.data.repository.PokeAPIRepository
import com.example.pokedexapp.data.PokeResponse

class GetPokemonsUseCase {

    private val respository = PokeAPIRepository()

    suspend operator fun invoke(limit: Int, offset:Int): PokeResponse = respository.getAllPokemons(limit, offset)
}