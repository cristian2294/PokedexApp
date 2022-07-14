package com.example.pokedexapp.ui.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.R

class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var ivPokemon: ImageView = itemView.findViewById(R.id.ivPokemon)
    var id: TextView = itemView.findViewById(R.id.tvidPokemon)
    var name: TextView = itemView.findViewById(R.id.tvNamePokemon)
    var linearLayout: LinearLayout = itemView.findViewById(R.id.containerPokemon)
}