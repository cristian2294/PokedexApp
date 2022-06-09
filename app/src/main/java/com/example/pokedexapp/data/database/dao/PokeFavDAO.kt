package com.example.pokedexapp.data.database.dao

import androidx.room.*
import com.example.pokedexapp.data.database.entities.PokeFavEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokeFavDAO {
    @Query("SELECT * FROM pokeFav ORDER BY id ASC")
    fun getAllFavoritesPokemon(): Flow<List<PokeFavEntity>>

    @Query("SELECT * FROM pokeFav WHERE name LIKE :name")
    suspend fun findPokeFavByName(name: String): PokeFavEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoritePokemon(pokemon: PokeFavEntity)

    @Delete
    suspend fun removeFavoritePokemon(pokemon: PokeFavEntity)
}