package com.example.pokedexapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.pokedexapp.data.Pokemon
import com.example.pokedexapp.databinding.ActivityDetailPokemonBinding
import com.example.pokedexapp.ui.viewmodel.DetailPokemonViewModel

// viewBinding
private lateinit var binding: ActivityDetailPokemonBinding

class DetailPokemon : AppCompatActivity() {

    // Init VM
    private val detailpokemonViewModel: DetailPokemonViewModel by viewModels()

    //UI variables
    private lateinit var ivDetailPokemon: ImageView
    private lateinit var tvDetailIdPokemon: TextView
    private lateinit var tvDetailNamePokemon: TextView
    private lateinit var tvDetailWeightPokemon: TextView
    private lateinit var tvDetailHeightPokemon: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent: Intent = intent
        val name = intent.getStringExtra("name")!!
        initUI()

        detailpokemonViewModel.getDetailPokemon(name)
        // Create the observer which updates the UI.
        val pokeObserver = Observer<Pokemon> { pokeResponse ->
            // Update the UI for show data of detail pokemon.
            tvDetailIdPokemon.text = "N° " + pokeResponse.id.toString()
            tvDetailNamePokemon.text = pokeResponse.name
            tvDetailWeightPokemon.text = (pokeResponse.weight/100).toString() + " Kg"
            tvDetailHeightPokemon.text = pokeResponse.height.toString() + " Mts"
            Glide.with(this).load(pokeResponse.sprites.other.officialArtwork.front_default)
                .into(ivDetailPokemon)
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        detailpokemonViewModel.pokeModel.observe(this, pokeObserver)
    }

    private fun initUI(){
         ivDetailPokemon = binding.ivDetailPokemon
         tvDetailIdPokemon = binding.tvDetailIdPokemon
         tvDetailNamePokemon = binding.tvDetailNamePokemon
         tvDetailWeightPokemon = binding.tvDetailWeightPokemon
         tvDetailHeightPokemon = binding.tvDetailHeightPokemon
    }
}