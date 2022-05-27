package com.example.pokedexapp.domain

import com.example.pokedexapp.data.database.entities.PokeFavEntity
import com.example.pokedexapp.data.repository.PokeRoomRepository


class AddFavoritePokemonUseCase(private val repository: PokeRoomRepository) {
    suspend operator fun invoke(pokeFavEntity: PokeFavEntity){
          repository.addFavoritePokemon(pokeFavEntity)
    }

}