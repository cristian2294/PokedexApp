package com.example.pokedexapp.data.repository

import androidx.annotation.WorkerThread
import com.example.pokedexapp.data.database.dao.PokeFavDAO
import com.example.pokedexapp.data.database.entities.PokeFavEntity
import kotlinx.coroutines.flow.Flow

class PokeRoomRepository(private val pokeFavDAO: PokeFavDAO) {

    // Declares the DAO as a private property in the constructor. Pass in the DAO
    // instead of the whole database, because you only need access to the DAO
    val allFavPokemons: Flow<List<PokeFavEntity>> = pokeFavDAO.getAllFavoritesPokemon()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addFavoritePokemon(pokemonFavEntity: PokeFavEntity) {
        pokeFavDAO.addFavoritePokemon(pokemonFavEntity)
    }
}