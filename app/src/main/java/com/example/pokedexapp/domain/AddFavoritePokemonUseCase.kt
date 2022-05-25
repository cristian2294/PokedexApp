package com.example.pokedexapp.domain

import com.example.pokedexapp.data.Pokemon
import com.example.pokedexapp.data.database.entities.PokeFavEntity
import com.example.pokedexapp.data.repository.PokeRoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddFavoritePokemonUseCase(private val repository: PokeRoomRepository) {

   // suspend operator fun invoke(pokeFavEntity: PokeFavEntity):
   //         Pokemon = repository.addFavoritePokemon(pokeFavEntity)

    /*
    fun addFavoritePokemon(pokeFavEntity: PokeFavEntity) = GlobalScope.launch(Dispatchers.Main) {
        withContext(Dispatchers.IO){
            repository.addFavoritePokemon(pokeFavEntity)
        }
    }
     */
}