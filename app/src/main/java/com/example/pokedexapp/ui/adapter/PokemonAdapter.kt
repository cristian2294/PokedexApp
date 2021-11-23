
package com.example.pokedexapp.ui.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.R
import com.example.pokedexapp.data.Result
import com.example.pokedexapp.ui.view.DetailPokemon
import com.example.pokedexapp.ui.viewholder.PokemonViewHolder

class PokemonAdapter(private val pokemonList: List<Result>)
    : RecyclerView.Adapter<PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutView = LayoutInflater.from(parent.context)
            .inflate(R.layout.template_list_pokemons, parent,false)

        return PokemonViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.name.text =  pokemon.name
        holder.url.text  = pokemon.url

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,DetailPokemon::class.java)
            intent.putExtra("name", pokemon.name)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }


}