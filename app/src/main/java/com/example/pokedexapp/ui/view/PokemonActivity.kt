package com.example.pokedexapp.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.data.PokeResponse
import com.example.pokedexapp.databinding.ActivityPokemonBinding
import com.example.pokedexapp.ui.adapter.PokemonAdapter
import com.example.pokedexapp.ui.viewmodel.PokemonViewModel


class PokemonActivity : AppCompatActivity() {

    // viewBinding
    private lateinit var binding:ActivityPokemonBinding

    private lateinit var searchView: SearchView

    // variables for recyclerView
    private var pokemonRv : RecyclerView? = null
    private lateinit var pokemondapter: PokemonAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    private val limit = 100000
    private var offset = 0
    private var isScrolling = true

    // Init VM
    private val pokemonViewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pokemonRv = binding.rvPokemons
        searchView = binding.searchViewPokemon

        isScrolling = true
        pokemonViewModel.getPokemons(limit, offset)

        // Create the observer which updates the UI.
        val pokeObserver = Observer<PokeResponse> { pokeResponse ->
            // Update the UI for show the data in the recyclerview
            layoutManager = GridLayoutManager(this,3)
            pokemonRv?.layoutManager = layoutManager
            pokemondapter = PokemonAdapter(pokeResponse.results, this)
            pokemonRv?.adapter = pokemondapter

            // for pagination

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    pokemondapter.filter.filter(query)
                    return false
                }

                //The onQueryTextChange method is called every time we type on the SearchView
                // and update the RecyclerView with the new results.
                override fun onQueryTextChange(newText: String?): Boolean {
                    pokemondapter.filter.filter(newText)
                    return false
                }

            })
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        pokemonViewModel.pokeModel.observe(this, pokeObserver)
    }
}