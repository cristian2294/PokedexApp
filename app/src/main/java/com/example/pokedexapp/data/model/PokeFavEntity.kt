package com.example.pokedexapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokeFav")
data class PokeFavEntity (
    @ColumnInfo(name = "poke_fav_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "poke_fav_name")
    val name: String,
    @ColumnInfo(name = "poke_fav_photo")
    val photo: String
        )
