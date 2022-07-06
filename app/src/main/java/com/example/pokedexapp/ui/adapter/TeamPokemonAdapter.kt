package com.example.pokedexapp.ui.adapter

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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
        /*
        Glide.with(context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${teamPokemon.id}.png")
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.ivTeamPokemon)
         */

        Glide.with(context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${teamPokemon.id}.png")
            .listener(object : RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("ImgError", "Image not working")
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    Palette.from(resource!!.toBitmap()).generate {
                            palette -> palette?.let {
                        val startColor =  it.dominantSwatch?.rgb?: 0
                        val endColor = 0
                        val gradientDrawable = GradientDrawable(
                            GradientDrawable.Orientation.TOP_BOTTOM,
                            intArrayOf(startColor, endColor)
                        )
                        holder.containerTeamPokemon.background = gradientDrawable
                    }
                    }
                    return false
                }
            })

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
        val messageSucces = "El pokemon se ha removido de tus favoritos exitosamente!"
        Toast.makeText(context, messageSucces, Toast.LENGTH_SHORT).show()
    }

    override fun getItemCount(): Int = teamPokemonList.size

    fun setData(teamPokemonList: List<PokeFavEntity>){
        this.teamPokemonList = teamPokemonList
        notifyDataSetChanged()
    }
}