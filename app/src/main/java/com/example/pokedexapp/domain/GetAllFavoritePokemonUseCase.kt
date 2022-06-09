package com.example.pokedexapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.pokedexapp.data.database.entities.PokeFavEntity
import com.example.pokedexapp.data.repository.PokeRoomRepository

class GetAllFavoritePokemonUseCase(private val repository: PokeRoomRepository) {

    fun  getAllFavoritePokemon():LiveData<List<PokeFavEntity>>{
        val allFavPokemon: LiveData<List<PokeFavEntity>> = repository.allFavPokemons.asLiveData()
        return allFavPokemon
    }
}