package com.example.pokedexapp.ui.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.R

class TeamPokemonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var ivTeamPokemon: ImageView = itemView.findViewById(R.id.iv_team_pokemon)
    var idTeamPokemon: TextView = itemView.findViewById(R.id.tv_id_team_pokemon)
    var nameTeamPokemon: TextView = itemView.findViewById(R.id.tv_name_team_pokemon)
    var btnTeamPokemon: ToggleButton = itemView.findViewById(R.id.btn_team_pokemon)
    var containerTeamPokemon: ConstraintLayout = itemView.findViewById(R.id.container_team_pokemon)
}