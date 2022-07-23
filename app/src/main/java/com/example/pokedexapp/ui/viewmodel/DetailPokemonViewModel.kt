package com.example.pokedexapp.ui.viewmodel

import androidx.lifecycle.*
import com.example.pokedexapp.data.model.Pokemon
import com.example.pokedexapp.data.database.entities.PokeFavEntity
import com.example.pokedexapp.data.repository.PokeRoomRepository
import com.example.pokedexapp.domain.AddFavoritePokemonUseCase
import com.example.pokedexapp.domain.GetDetailPokemonUseCase
import com.example.pokedexapp.domain.GetAllFavoritePokemonUseCase
import com.example.pokedexapp.domain.RemoveFavoritePokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class DetailPokemonViewModel @Inject constructor(
    private val repository: PokeRoomRepository,
    private val getDetailPokemonUseCase: GetDetailPokemonUseCase,
    private val addFavoritePokemonUseCase: AddFavoritePokemonUseCase,
    private val getAllFavoritePokemonUseCase: GetAllFavoritePokemonUseCase,
    private val removeFavoritePokemonUseCase: RemoveFavoritePokemonUseCase
    ): ViewModel() {

    val pokeModel = MutableLiveData<Pokemon>()
    private lateinit var allFavoritePokemon:LiveData<List<PokeFavEntity>>


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

    /**
     * Launching a new coroutine to remove the data in a non-blocking way
     */
    fun removeFavoritePokemon(pokeFavEntity: PokeFavEntity) = viewModelScope.launch {
        withContext(Dispatchers.IO){
            removeFavoritePokemonUseCase(pokeFavEntity)
        }
    }

    fun getAllFavoritePokemons():LiveData<List<PokeFavEntity>>{
        allFavoritePokemon =  getAllFavoritePokemonUseCase.getAllFavoritePokemon()
        return allFavoritePokemon
    }

}

class DetailpokemonViewModelFactory @Inject constructor(private val repository: PokeRoomRepository,
                                                        private val getDetailPokemonUseCase: GetDetailPokemonUseCase,
                                                        private val addFavoritePokemonUseCase: AddFavoritePokemonUseCase,
                                                        private val getAllFavoritePokemonUseCase: GetAllFavoritePokemonUseCase,
                                                        private val removeFavoritePokemonUseCase: RemoveFavoritePokemonUseCase
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailPokemonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailPokemonViewModel(repository ,
                getDetailPokemonUseCase,
                addFavoritePokemonUseCase,
                getAllFavoritePokemonUseCase,
                removeFavoritePokemonUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}