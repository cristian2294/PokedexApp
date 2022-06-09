package com.example.pokedexapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pokedexapp.R
import com.example.pokedexapp.data.database.entities.PokeFavEntity
import com.example.pokedexapp.ui.viewholder.TeamPokemonViewHolder

class TeamPokemonAdapter(private val context: Context):RecyclerView.Adapter<TeamPokemonViewHolder>() {

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
    }

    override fun getItemCount(): Int = teamPokemonList.size

    fun setData(teamPokemonList: List<PokeFavEntity>){
        this.teamPokemonList = teamPokemonList
        notifyDataSetChanged()
    }
}