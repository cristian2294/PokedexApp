package com.example.pokedexapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.pokedexapp.data.PokeType
import com.example.pokedexapp.data.Pokemon
import com.example.pokedexapp.databinding.ActivityDetailPokemonBinding
import com.example.pokedexapp.ui.viewmodel.DetailPokemonViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

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
    private lateinit var tvDetailTypePokemon1: TextView
    private lateinit var tvDetailTypePokemon2: TextView
    private lateinit var bottonNavigationView: BottomNavigationView
    private lateinit var types: List<PokeType>

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
            tvDetailWeightPokemon.text = ((pokeResponse.weight)*100f/1000f).toString() + " Kg"
            tvDetailHeightPokemon.text = pokeResponse.height.toString() + " Mts"
            types = pokeResponse.types

            tvDetailTypePokemon1.text = types[0].type.name

            tvDetailTypePokemon2.isVisible = false

            // This if is for validating nullability in the second pokemon type
            if (types.size >= 2){
                tvDetailTypePokemon2.text = types[1].type.name
                tvDetailTypePokemon2.isVisible = true
            }

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
         tvDetailTypePokemon1 = binding.tvDetailTypePokemon1
         tvDetailTypePokemon2 = binding.tvDetailTypePokemon2
         bottonNavigationView = binding.bottomNavigationView

         bottonNavigationView.setBackgroundColor(0)
    }
}