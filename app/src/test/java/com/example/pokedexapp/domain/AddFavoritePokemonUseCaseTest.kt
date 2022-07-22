package com.example.pokedexapp.domain

import com.example.pokedexapp.data.database.entities.PokeFavEntity
import com.example.pokedexapp.data.repository.PokeRoomRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class AddFavoritePokemonUseCaseTest{

    @RelaxedMockK
    private lateinit var pokeRoomRepository: PokeRoomRepository

    lateinit var addFavoritePokemonUseCase: AddFavoritePokemonUseCase

    @Before
    fun obBefore(){
        MockKAnnotations.init(this)
        addFavoritePokemonUseCase = AddFavoritePokemonUseCase(pokeRoomRepository)
    }

    @Test
    fun  `when existing an entity it adds a favorite pokemon`() = runBlocking{

        //Given
        val mockPokeFavEntity = PokeFavEntity(0,"pikachu",3,1,"https://link1")

        //When
        addFavoritePokemonUseCase(mockPokeFavEntity)

        //Then
        coVerify(exactly = 1) { pokeRoomRepository.addFavoritePokemon(any()) }
    }
}