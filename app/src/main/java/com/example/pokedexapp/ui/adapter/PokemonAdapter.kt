package com.example.pokedexapp.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
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
import com.example.pokedexapp.data.model.Result
import com.example.pokedexapp.ui.view.DetailPokemon
import com.example.pokedexapp.ui.viewholder.PokemonViewHolder
import java.util.*
import kotlin.collections.ArrayList

class PokemonAdapter(private val pokemonList: ArrayList<Result>, private val context: Context)
    : RecyclerView.Adapter<PokemonViewHolder>(), Filterable {

    var pokemonFilterList = ArrayList<Result>()

    init {
        pokemonFilterList = pokemonList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutView = LayoutInflater.from(parent.context)
            .inflate(R.layout.template_list_pokemons, parent,false)

        return PokemonViewHolder(layoutView)
    }

    override fun getItemCount(): Int = pokemonFilterList.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {

        val pokemon = pokemonFilterList[position]
        val partsUrl  = pokemon.url.split("/")
        val number = partsUrl[partsUrl.size-2]
        val uri = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$number.png"

        holder.id.text = "N° ${(number)}"
        holder.name.text =  pokemon.name
        Glide.with(context)
            .load(uri).listener(object : RequestListener<Drawable>{
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
                        holder.linearLayout.background = gradientDrawable
                    }
                    }
                    return false
                }

            })
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.ivPokemon)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,DetailPokemon::class.java)
            intent.putExtra("name", pokemon.name)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter(){

            //checks if we have typed a text in the SeachView.
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charSearch = p0.toString()

                //If there is not any text, will return all items
                pokemonFilterList = if (charSearch.isEmpty()){
                    pokemonList
                }else{
                    val resultList = ArrayList<Result>()

                    for (i in pokemonList){
                        if (i.name.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ){
                            resultList.add(i)
                        }
                    }
                    resultList
                }
                val filterResult = FilterResults()
                filterResult.values = pokemonFilterList
                return filterResult
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                pokemonFilterList = p1?.values as ArrayList<Result>
                notifyDataSetChanged()
            }

        }
    }

}