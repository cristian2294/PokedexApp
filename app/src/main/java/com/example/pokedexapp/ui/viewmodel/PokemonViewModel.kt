package com.example.pokedexapp.ui.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.data.model.PokeResponse
import com.example.pokedexapp.domain.GetPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase
) : ViewModel(){

    val pokeModel = MutableLiveData<PokeResponse>()

    fun getPokemons(limit: Int, offset: Int){
        viewModelScope.launch {
            val pokeResponse = withContext(Dispatchers.IO){
                getPokemonsUseCase(limit, offset)
            }
            pokeModel.value = pokeResponse
        }
    }
}