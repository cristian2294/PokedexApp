package com.example.pokedexapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.data.database.PokeApplication
import com.example.pokedexapp.databinding.ActivityTeamPokemonBinding
import com.example.pokedexapp.ui.adapter.TeamPokemonAdapter
import com.example.pokedexapp.ui.viewmodel.DetailPokemonViewModel
import com.example.pokedexapp.ui.viewmodel.DetailpokemonViewModelFactory

class TeamPokemon : AppCompatActivity() {

    // viewBinding
    private lateinit var binding: ActivityTeamPokemonBinding

    // variables for recyclerView
    private var teamPokemonList : RecyclerView? = null
    private lateinit var teamPokemondapter: TeamPokemonAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    // Init VM
    private val detailpokemonViewModel: DetailPokemonViewModel by viewModels{
        DetailpokemonViewModelFactory((application as PokeApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailpokemonViewModel.getAllFavoritePokemons().observe(this) { teamPokemon ->
            teamPokemonList = binding.rvTeamPokemon
            layoutManager = LinearLayoutManager(this)
            teamPokemonList?.layoutManager = layoutManager
            teamPokemondapter = TeamPokemonAdapter(this, detailpokemonViewModel)
            teamPokemonList?.adapter = teamPokemondapter
            teamPokemondapter.setData(teamPokemon)
        }
    }
}