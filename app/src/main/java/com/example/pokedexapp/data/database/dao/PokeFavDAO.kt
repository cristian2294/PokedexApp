package com.example.pokedexapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pokedexapp.data.database.entities.PokeFavEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokeFavDAO {
    @Query("SELECT * FROM pokeFav")
    fun getAllFavoritesPokemon(): Flow<List<PokeFavEntity>>

    @Query("SELECT * FROM pokeFav WHERE name LIKE :name")
    suspend fun findPokeFavByName(name: String): PokeFavEntity

    @Insert
    suspend fun addFavoritePokemon(pokemon: PokeFavEntity)

    @Delete
    suspend fun removeFavoritePokemon(pokemon: PokeFavEntity)
}