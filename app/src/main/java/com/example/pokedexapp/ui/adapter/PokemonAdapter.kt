
package com.example.pokedexapp.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pokedexapp.R
import com.example.pokedexapp.data.Result
import com.example.pokedexapp.ui.view.DetailPokemon
import com.example.pokedexapp.ui.viewholder.PokemonViewHolder

class PokemonAdapter(private val pokemonList: List<Result>, private val context: Context)
    : RecyclerView.Adapter<PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutView = LayoutInflater.from(parent.context)
            .inflate(R.layout.template_list_pokemons, parent,false)

        return PokemonViewHolder(layoutView)
    }


    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.id.text = "N° ${(position+1)}"
        holder.name.text =  pokemon.name
        Glide.with(context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+(position+1).toString()+".png")
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.ivPokemon)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,DetailPokemon::class.java)
            intent.putExtra("name", pokemon.name)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = pokemonList.size
}