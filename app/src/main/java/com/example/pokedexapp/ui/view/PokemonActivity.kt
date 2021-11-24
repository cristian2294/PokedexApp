package com.example.pokedexapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.data.PokeResponse
import com.example.pokedexapp.databinding.ActivityPokemonBinding
import com.example.pokedexapp.ui.adapter.PokemonAdapter
import com.example.pokedexapp.ui.viewmodel.PokemonViewModel

// viewBinding
private lateinit var binding:ActivityPokemonBinding

// variables for recyclerView
private  var pokemonList : RecyclerView? = null
private lateinit var pokemondapter: PokemonAdapter
private lateinit var layoutManager: RecyclerView.LayoutManager

class PokemonActivity : AppCompatActivity() {

    // Init VM
    private val pokemonViewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pokemonList = binding.rvPokemons

        pokemonViewModel.getPokemons()

        // Create the observer which updates the UI.
        val pokeObserver = Observer<PokeResponse> { pokeResponse ->
            // Update the UI for show the data in the recyclerview
            layoutManager = GridLayoutManager(this,3)
            pokemonList?.layoutManager = layoutManager
            pokemondapter = PokemonAdapter(pokeResponse.results, this)
            pokemonList?.adapter = pokemondapter
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        pokemonViewModel.pokeModel.observe(this, pokeObserver)
    }

}