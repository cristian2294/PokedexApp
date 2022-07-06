package com.example.pokedexapp.ui.view

import android.content.Intent
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokedexapp.R
import com.example.pokedexapp.data.PokeType
import com.example.pokedexapp.data.Pokemon
import com.example.pokedexapp.data.database.PokeApplication
import com.example.pokedexapp.data.database.entities.PokeFavEntity
import com.example.pokedexapp.databinding.ActivityDetailPokemonBinding
import com.example.pokedexapp.ui.viewmodel.DetailPokemonViewModel
import com.example.pokedexapp.ui.viewmodel.DetailpokemonViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class DetailPokemon : AppCompatActivity() {

    // viewBinding
    private lateinit var binding: ActivityDetailPokemonBinding

    // Init VM
    private val detailpokemonViewModel: DetailPokemonViewModel by viewModels{
        DetailpokemonViewModelFactory((application as PokeApplication).repository)
    }

    //UI variables
    private lateinit var ivDetailPokemon: ImageView
    private lateinit var tvDetailIdPokemon: TextView
    private lateinit var tvDetailNamePokemon: TextView
    private lateinit var tvDetailWeightPokemon: TextView
    private lateinit var tvDetailHeightPokemon: TextView
    private lateinit var tvDetailTypePokemon1: TextView
    private lateinit var tvDetailTypePokemon2: TextView
    private lateinit var bottonNavigationView: BottomNavigationView
    private lateinit var btnFavPokemon: FloatingActionButton
    private lateinit var constraintLayout: ConstraintLayout

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
            tvDetailIdPokemon.text = "N° ${pokeResponse.id}"
            tvDetailNamePokemon.text = pokeResponse.name
            tvDetailWeightPokemon.text = "${((pokeResponse.weight)*100f/1000f)} Kg"
            tvDetailHeightPokemon.text = "${pokeResponse.height/10f} Mts"
            types = pokeResponse.types

            tvDetailTypePokemon1.text = types[0].type.name

            tvDetailTypePokemon2.isVisible = false

            // This if is for validating nullability in the second pokemon type
            if (types.size >= 2){
                tvDetailTypePokemon2.text = types[1].type.name
                tvDetailTypePokemon2.isVisible = true
            }

            Glide.with(this)
                .load(pokeResponse.sprites.other.officialArtwork.front_default).listener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.d("DetailImgError", "Image not working")
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Palette.from(resource!!.toBitmap()).generate {
                                palette -> palette?.let {
                            val startColor =  R.color.black
                            val endColor = it.dominantSwatch?.rgb?: 0
                            val gradientDrawable = GradientDrawable(
                                GradientDrawable.Orientation.TOP_BOTTOM,
                                intArrayOf(startColor,endColor)
                            )
                            constraintLayout.background = gradientDrawable
                        }
                        }
                        return false
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivDetailPokemon)

            btnFavPokemon.setOnClickListener {
                addFavoritePokemon(pokeResponse)
            }

            bottonNavigationView.setOnItemSelectedListener{ item ->
                when(item.itemId){
                    R.id.team -> {
                        val intent = Intent(this, TeamPokemon::class.java)
                        startActivity(intent)
                        true
                    }

                    R.id.home -> {
                        //Toast.makeText(this, "HOME", Toast.LENGTH_SHORT).show()
                        //TODO implement back home
                        true
                    }

                    else -> false
                }

            }
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
         btnFavPokemon = binding.btnFavPokemon
         constraintLayout = binding.containerDetailPokemon

         bottonNavigationView.setBackgroundColor(0)
    }

    private fun addFavoritePokemon(pokeResponse: Pokemon){
        val pokeFavEntity = PokeFavEntity(
            pokeResponse.id,
            pokeResponse.name,
            pokeResponse.height,
            pokeResponse.weight,
            pokeResponse.sprites.other.officialArtwork.front_default,
            //pokeResponse.types
        )
        detailpokemonViewModel.addFavoritePokemon(pokeFavEntity)
        val messageSucces = getString(R.string.add_favorite_pokemon)
        Toast.makeText(this, messageSucces, Toast.LENGTH_SHORT).show()
    }
}