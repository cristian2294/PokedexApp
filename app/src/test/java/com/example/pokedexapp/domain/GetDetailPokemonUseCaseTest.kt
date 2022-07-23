package com.example.pokedexapp.domain

import com.example.pokedexapp.data.model.*
import com.example.pokedexapp.data.repository.PokeAPIRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetDetailPokemonUseCaseTest {

    @RelaxedMockK
    private lateinit var pokeAPIRepository: PokeAPIRepository

    lateinit var getDetailPokemonUseCase: GetDetailPokemonUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getDetailPokemonUseCase = GetDetailPokemonUseCase(pokeAPIRepository)
    }

    @Test
    fun `when exist a response return a pokemon`() = runBlocking{

        //Given
        val mockOfficialArtwork = OfficialArtwork("https://link0")
        val mockOther = Other(mockOfficialArtwork)
        val mockSprite = Sprite("https://link1","https://link2",mockOther )
        val mockType = Type("type", "url")
        val mockListTypes = listOf(PokeType(1,mockType))
        val mockPokemon = Pokemon(1,"pikachu",1,3,mockSprite,mockListTypes)
        coEvery { pokeAPIRepository.getDetailPokemon(any()) } returns mockPokemon

        //When
        val response = getDetailPokemonUseCase("pikachu")

        //Then
        coVerify(exactly = 1) {  pokeAPIRepository.getDetailPokemon(any()) }
        assert(response == mockPokemon)
    }
}