package com.example.pokedexapp.ui.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.data.PokeResponse
import com.example.pokedexapp.domain.GetPokemonsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonViewModel: ViewModel(){

    val pokeModel = MutableLiveData<PokeResponse>()
    var getPokemonsUseCase = GetPokemonsUseCase()

    fun getPokemons(){
        viewModelScope.launch {
            val pokeResponse = withContext(Dispatchers.IO){
                getPokemonsUseCase()
            }
            pokeModel.value = pokeResponse
        }
    }
}