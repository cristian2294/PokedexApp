package com.example.pokedexapp.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.pokedexapp.data.database.entities.PokeFavEntity
import com.example.pokedexapp.data.repository.PokeRoomRepository
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetAllFavoritePokemonUseCaseTest{

    @RelaxedMockK
    private lateinit var pokeRoomRepository: PokeRoomRepository

    lateinit var getallFavoritePokemonUseCase: GetAllFavoritePokemonUseCase

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getallFavoritePokemonUseCase = GetAllFavoritePokemonUseCase(pokeRoomRepository)
    }

    @Test
    fun `when the database is not empty return a favorite pokemon list`(){
        //Given
        val list: LiveData<PokeFavEntity> = listOf(PokeFavEntity(0,
            "pikachu",
            1,
            3,
            "https://url1"),PokeFavEntity
        (1,
        "charmander",1,2,"https://url2")).asFlow().asLiveData()

        //When
        getallFavoritePokemonUseCase.getAllFavoritePokemon()

        //Then
        coVerify(exactly = 1){ pokeRoomRepository.allFavPokemons }
    }

}