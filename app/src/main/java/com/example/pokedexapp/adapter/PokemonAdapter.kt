
package com.example.pokedexapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.R
import com.example.pokedexapp.data.PokeResponse
import com.example.pokedexapp.data.Pokemon
import com.example.pokedexapp.data.Result
import com.example.pokedexapp.viewholder.PokemonViewHolder
import com.squareup.picasso.Picasso

class PokemonAdapter(private val pokemonList: List<Pokemon>)
    : RecyclerView.Adapter<PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutView = LayoutInflater.from(parent.context)
            .inflate(R.layout.template_list_pokemons, parent,false)

        return PokemonViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.id.text = pokemon.id.toString()
        holder.name.text = pokemon.name
        holder.height.text = pokemon.height.toString()
        holder.weight.text = pokemon.weight.toString()

        val uri = pokemon.images.frontDefault
        Picasso.get()
            .load(uri)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }


}