package com.example.pokedexapp.ui.adapter

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pokedexapp.R
import com.example.pokedexapp.data.database.entities.PokeFavEntity
import com.example.pokedexapp.ui.viewholder.TeamPokemonViewHolder
import com.example.pokedexapp.ui.viewmodel.DetailPokemonViewModel

class TeamPokemonAdapter(private val context: Context,
                         private val detailpokemonViewModel: DetailPokemonViewModel): RecyclerView.Adapter<TeamPokemonViewHolder>() {

    private var teamPokemonList = emptyList<PokeFavEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamPokemonViewHolder {
        val layoutView = LayoutInflater.from(parent.context)
            .inflate(R.layout.template_list_team_pokemon, parent,false)

        return TeamPokemonViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: TeamPokemonViewHolder, position: Int) {
        val teamPokemon = teamPokemonList[position]
        holder.idTeamPokemon.text = "N° ${(position+1)}"
        holder.nameTeamPokemon.text =  teamPokemon.name
        Glide.with(context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${teamPokemon.id}.png")
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.ivTeamPokemon)

        holder.btnTeamPokemon.setOnClickListener {
            if ( holder.btnTeamPokemon.isChecked == false) {
                removeFavoritePokemon(teamPokemonList[position])
            }
        }
    }

    private fun removeFavoritePokemon(pokeFavEntity: PokeFavEntity) {
        detailpokemonViewModel.removeFavoritePokemon(pokeFavEntity)
        val messageSucces = Resources.getSystem().getString(R.string.remove_favorite_pokemon)
        Toast.makeText(context, messageSucces, Toast.LENGTH_SHORT).show()
    }

    override fun getItemCount(): Int = teamPokemonList.size

    fun setData(teamPokemonList: List<PokeFavEntity>){
        this.teamPokemonList = teamPokemonList
        notifyDataSetChanged()
    }
}