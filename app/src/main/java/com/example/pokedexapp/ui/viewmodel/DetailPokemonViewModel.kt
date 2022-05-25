package com.example.pokedexapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.data.Pokemon
import com.example.pokedexapp.data.database.entities.PokeFavEntity
import com.example.pokedexapp.domain.AddFavoritePokemonUseCase
import com.example.pokedexapp.domain.GetDetailPokemonUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailPokemonViewModel: ViewModel() {

    val pokeModel = MutableLiveData<Pokemon>()
    val getDetailPokemonUseCase = GetDetailPokemonUseCase()
    //val addFavoritePokemonUseCase = AddFavoritePokemonUseCase()

    fun getDetailPokemon(name: String){
        viewModelScope.launch {
            val pokemon =  withContext(Dispatchers.IO){
                getDetailPokemonUseCase(name)
            }
            pokeModel.value = pokemon
        }
    }

    /*
    fun addFavoritePokemon(pokeFavEntity: PokeFavEntity){
        viewModelScope.launch {
            val pokemon = withContext(Dispatchers.IO){
                addFavoritePokemonUseCase(pokeFavEntity)
            }
            pokeModel.value = pokemon
        }
    }
     */
}