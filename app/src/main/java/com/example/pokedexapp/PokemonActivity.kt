package com.example.pokedexapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pokedexapp.model.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        val retrofit = getRetrofit()
        val service = retrofit.create(APIService::class.java)
        val call =  service.getAllPokemons(150,0)

        call.enqueue(object : Callback<PokeResponse> {
            override fun onResponse(call: Call<PokeResponse>, response: Response<PokeResponse>) {

                if (response.isSuccessful){
                    response.body()?.results?.let { list ->

                        // FILL THE RECYCLERVIEW
                        Log.d("RESULT", response.body().toString())
                    }
                }
            }
            override fun onFailure(call: Call<PokeResponse>, t: Throwable) {
                Log.d("Error", ""+t.printStackTrace())
            }

        })
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}