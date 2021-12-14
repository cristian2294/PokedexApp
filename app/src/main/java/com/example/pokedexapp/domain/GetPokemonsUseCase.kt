package com.example.pokedexapp.domain

import com.example.pokedexapp.data.repository.PokeRepository
import com.example.pokedexapp.data.PokeResponse

class GetPokemonsUseCase {

    private val respository = PokeRepository()

    suspend operator fun invoke(limit: Int, offset:Int): PokeResponse = respository.getAllPokemons(limit, offset)
}