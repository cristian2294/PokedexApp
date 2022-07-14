package com.example.pokedexapp.data.database

import android.app.Application
import com.example.pokedexapp.data.repository.PokeRoomRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class PokeApplication: Application() {

    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { PokeFavDb.getDatabase(this) }
    val repository by lazy { PokeRoomRepository(database.getDAO()) }
}

