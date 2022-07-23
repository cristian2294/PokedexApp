package com.example.pokedexapp.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pokedexapp.data.model.PokeResponse
import com.example.pokedexapp.data.model.Result
import com.example.pokedexapp.domain.GetPokemonsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PokemonViewModelTest{

    @RelaxedMockK
    private lateinit var getPokemonsUseCase: GetPokemonsUseCase

    lateinit var pokemonViewModel: PokemonViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        pokemonViewModel = PokemonViewModel(getPokemonsUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when existing limit and offset return a result list`() = runTest() {
        //Given
        val mockResult = Result("pikachu", "https//link",1)
        val mockArrayListPokemon = ArrayList<Result>()
        mockArrayListPokemon.add(mockResult)
        val mockPokeResponse = PokeResponse(mockArrayListPokemon)
        coEvery { getPokemonsUseCase(any(),any()) } returns mockPokeResponse

        //When
        pokemonViewModel.getPokemons(10000,1)

        //Then
        coVerify(exactly = 1) { getPokemonsUseCase(any(),any()) }
    }
}