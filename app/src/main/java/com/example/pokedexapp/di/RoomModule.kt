package com.example.pokedexapp.di

import android.content.Context
import androidx.room.Room
import com.example.pokedexapp.data.database.PokeFavDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val POKE_FAV_DATABASE_NAME = "favoritesPokemonDatabase"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(context,
        PokeFavDb::class.java,
        POKE_FAV_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun providePokeFavDAO(db:PokeFavDb) = db.getDAO()
}