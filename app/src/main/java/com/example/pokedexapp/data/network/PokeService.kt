package com.example.pokedexapp.data.network

import android.util.Log
import com.example.pokedexapp.core.RetrofitHelper
import com.example.pokedexapp.data.PokeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PokeService {
    
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getPokemons(): PokeResponse? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PokeApiClient::class.java).getAllPokemons(150, 0)
            response.body()

        }
    }

}