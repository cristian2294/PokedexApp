package com.example.pokedexapp.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [PokeFavEntity::class],
    version = 1
)
abstract class PokeFavDb: RoomDatabase() {
    companion object {
        private var INSTANCE: PokeFavDb? = null

        fun getAppDatabase(context: Context): PokeFavDb? {
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    PokeFavDb::class.java,
                    "favoritesPokemonDatabase"
                ).allowMainThreadQueries()
                    .build()
            }

            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
    abstract fun getDAO() : PokeFavDAO
}
