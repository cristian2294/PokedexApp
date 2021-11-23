package com.example.pokedexapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.pokedexapp.data.PokeResponse
import com.example.pokedexapp.data.Pokemon
import com.example.pokedexapp.databinding.ActivityDetailPokemonBinding
import com.example.pokedexapp.ui.adapter.PokemonAdapter
import com.example.pokedexapp.ui.viewmodel.DetailPokemonViewModel
import com.example.pokedexapp.ui.viewmodel.PokemonViewModel

// viewBinding
private lateinit var binding: ActivityDetailPokemonBinding

private lateinit var name: String

class DetailPokemon : AppCompatActivity() {

    // Init VM
    private val detailpokemonViewModel: DetailPokemonViewModel by viewModels()

    //UI variables
    private lateinit var ivDetailPokemon: ImageView
    private lateinit var tvDetailNamePokemon: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent: Intent = getIntent()
        val name = intent.getStringExtra("name")!!
        initUI()

        detailpokemonViewModel.getDetailPokemon(name)
        Log.d("NAME", name)
        // Create the observer which updates the UI.
        val pokeObserver = Observer<Pokemon> { pokeResponse ->
            // Update the UI for show data of detail pokemon.
            tvDetailNamePokemon.text = pokeResponse.name
            Glide.with(this).load(pokeResponse.sprites.front_default)
                .into(ivDetailPokemon)
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        detailpokemonViewModel.pokeModel.observe(this, pokeObserver)
    }

    private fun initUI(){
         ivDetailPokemon = binding.ivDetailPokemon
         tvDetailNamePokemon = binding.tvDetailNamePokemon
    }
}