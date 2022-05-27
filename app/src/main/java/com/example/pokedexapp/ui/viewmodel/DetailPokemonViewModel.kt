package com.example.pokedexapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.data.Pokemon
import com.example.pokedexapp.data.database.entities.PokeFavEntity
import com.example.pokedexapp.data.repository.PokeRoomRepository
import com.example.pokedexapp.domain.AddFavoritePokemonUseCase
import com.example.pokedexapp.domain.GetDetailPokemonUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailPokemonViewModel(private val repository: PokeRoomRepository): ViewModel() {

    val pokeModel = MutableLiveData<Pokemon>()
    val getDetailPokemonUseCase = GetDetailPokemonUseCase()
    val addFavoritePokemonUseCase = AddFavoritePokemonUseCase(repository)

    fun getDetailPokemon(name: String){
        viewModelScope.launch {
            val pokemon =  withContext(Dispatchers.IO){
                getDetailPokemonUseCase(name)
            }
            pokeModel.value = pokemon
        }
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun addFavoritePokemon(pokeFavEntity: PokeFavEntity) = viewModelScope.launch {
        withContext(Dispatchers.IO){
            addFavoritePokemonUseCase(pokeFavEntity)
        }
    }
}

class DetailpokemonViewModelFactory(private val repository: PokeRoomRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailPokemonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailPokemonViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}