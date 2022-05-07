package com.example.pokedexapp.data.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PokeFavDAO {
    @Query("SELECT * FROM pokeFav")
    fun getAllFavoritesPokemon(): List<PokeFavEntity>

    @Query("SELECT * FROM pokeFav WHERE poke_fav_name LIKE :name")
    fun findPokeFavByName(name: String): PokeFavEntity

    @Insert
    fun addFavoritePokemon(pokemon: PokeFavEntity)

    @Delete
    fun removeFavoritePokemon(pokemon: PokeFavEntity)
}