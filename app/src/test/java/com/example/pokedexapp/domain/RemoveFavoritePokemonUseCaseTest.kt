package com.example.pokedexapp.domain

import com.example.pokedexapp.data.database.entities.PokeFavEntity
import com.example.pokedexapp.data.repository.PokeRoomRepository
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RemoveFavoritePokemonUseCaseTest{

    @RelaxedMockK
    private lateinit var pokeRoomRepositoty: PokeRoomRepository

    lateinit var removeFavoritePokemonUseCase: RemoveFavoritePokemonUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        removeFavoritePokemonUseCase = RemoveFavoritePokemonUseCase(pokeRoomRepositoty)
    }

    @Test
    fun `when existing an entity it removes a favorite pokemon`() = runBlocking{

        //Given
        val mockPokeFavEntity = PokeFavEntity(1,"pikachu",1,3,"https://link1")

        //When
        removeFavoritePokemonUseCase(mockPokeFavEntity)

        //Then
        coVerify(exactly = 1) { pokeRoomRepositoty.removeFavoritePokemon(any()) }
    }
}