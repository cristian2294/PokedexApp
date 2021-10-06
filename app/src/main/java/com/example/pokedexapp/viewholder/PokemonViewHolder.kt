package com.example.pokedexapp.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.R


class PokemonViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    var id: TextView = itemView.findViewById(R.id.tvIdPokemon)
    var image: ImageView = itemView.findViewById(R.id.ivPokemon)
    var name: TextView = itemView.findViewById(R.id.tvNamePokemon)
    var height: TextView = itemView.findViewById(R.id.tvHeightPokemon)
    var weight: TextView = itemView.findViewById(R.id.tvWeightPokemon)

}