package com.example.pokedexapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.adapter.PokemonAdapter
import com.example.pokedexapp.data.PokeResponse
import com.example.pokedexapp.data.network.PokeApiClient
import com.example.pokedexapp.databinding.ActivityMainBinding
import com.example.pokedexapp.ui.viewmodel.PokemonViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PokemonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // variables for recyclerView
    private var pokemonList : RecyclerView? = null
    private lateinit var pokemondapter: PokemonAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    private val pokemonViewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            pokemonViewModel.onCreate()
            pokemonViewModel.getPokemonsUseCase
        }
    }
}