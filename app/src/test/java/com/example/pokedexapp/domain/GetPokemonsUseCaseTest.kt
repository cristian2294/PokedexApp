package com.example.pokedexapp.domain

import com.example.pokedexapp.data.model.PokeResponse
import com.example.pokedexapp.data.model.Result
import com.example.pokedexapp.data.repository.PokeAPIRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetPokemonsUseCaseTest{

    @RelaxedMockK
    private lateinit var pokeAPIRepository: PokeAPIRepository

    lateinit var getPokemonsUseCase: GetPokemonsUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getPokemonsUseCase = GetPokemonsUseCase(pokeAPIRepository)
    }

    @Test
    fun `when the API return something then get values from the API `() = runBlocking{

        // Given
        val mockResult = Result("pikachu", "https//link",1)
        val mockArrayListPokemon = ArrayList<Result>()
        mockArrayListPokemon.add(mockResult)
        val mockPokeResponse = PokeResponse(mockArrayListPokemon)
        coEvery { pokeAPIRepository.getAllPokemons(any(),any()) } returns mockPokeResponse

        // When
        val response = getPokemonsUseCase(1,1)

        // Then
        coVerify(exactly = 1) { pokeAPIRepository.getAllPokemons(any(),any()) }
        assert(mockPokeResponse == response)

    }

}