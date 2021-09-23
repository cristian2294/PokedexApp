package com.example.pokedexapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.data.PokeResponse
import com.example.pokedexapp.domain.GetPokemonsUseCase
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel(){

    val PokeModel = MutableLiveData<PokeResponse>()

    var getPokemonsUseCase = GetPokemonsUseCase()
    fun onCreate(){
        viewModelScope.launch {
            val responseResult = getPokemonsUseCase()
            Log.d("resultado", responseResult.toString())
            // FILL RECYCLERVIEW
        }
    }

}