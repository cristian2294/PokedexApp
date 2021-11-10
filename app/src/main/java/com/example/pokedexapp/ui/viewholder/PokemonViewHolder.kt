package com.example.pokedexapp.ui.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.R


class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var name: TextView = itemView.findViewById(R.id.tvNamePokemon)
    var url: TextView = itemView.findViewById(R.id.tvUrlPokemon)
}