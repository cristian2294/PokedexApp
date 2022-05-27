package com.example.pokedexapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokedexapp.data.PokeType
import com.example.pokedexapp.data.Pokemon
import com.example.pokedexapp.data.Sprite


@Entity(tableName = "pokeFav")
data class PokeFavEntity (
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "height")
    var height: Int,
    @ColumnInfo(name = "weight")
    var weight: Int,
    @ColumnInfo(name = "url_photo")
    var urlPhoto: String,
    //@ColumnInfo(name = "types")
    //var types: List<PokeType>
    )
