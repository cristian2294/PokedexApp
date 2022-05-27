package com.example.pokedexapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pokedexapp.data.database.dao.PokeFavDAO
import com.example.pokedexapp.data.database.entities.PokeFavEntity

@Database(
    entities = [PokeFavEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PokeFavDb: RoomDatabase() {

    abstract fun getDAO() : PokeFavDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PokeFavDb? = null

        fun getDatabase(context: Context): PokeFavDb {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PokeFavDb::class.java,
                    "favoritesPokemonDatabase"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
