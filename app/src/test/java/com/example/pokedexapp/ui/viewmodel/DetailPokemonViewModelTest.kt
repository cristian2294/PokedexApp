package com.example.pokedexapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.pokedexapp.data.database.entities.PokeFavEntity
import com.example.pokedexapp.data.model.*
import com.example.pokedexapp.data.repository.PokeRoomRepository
import com.example.pokedexapp.domain.AddFavoritePokemonUseCase
import com.example.pokedexapp.domain.GetAllFavoritePokemonUseCase
import com.example.pokedexapp.domain.GetDetailPokemonUseCase
import com.example.pokedexapp.domain.RemoveFavoritePokemonUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailPokemonViewModelTest{

    @RelaxedMockK
    private lateinit var pokeRoomRepository: PokeRoomRepository

    @RelaxedMockK
    private lateinit var getDetailPokemonUseCase: GetDetailPokemonUseCase

    @RelaxedMockK
    private lateinit var addFavoritePokemonUseCase: AddFavoritePokemonUseCase

    @RelaxedMockK
    private lateinit var getAllFavoritePokemonUseCase: GetAllFavoritePokemonUseCase

    @RelaxedMockK
    private lateinit var removeFavoritePokemonUseCase: RemoveFavoritePokemonUseCase

    lateinit var detailPokemonViewModel: DetailPokemonViewModel

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        detailPokemonViewModel = DetailPokemonViewModel(
            pokeRoomRepository,
            getDetailPokemonUseCase,
            addFavoritePokemonUseCase,
            getAllFavoritePokemonUseCase,
            removeFavoritePokemonUseCase
        )

        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when existing a name return a pokemon detail `() = runTest(){
        //Given
        val mockOfficialArtwork = OfficialArtwork("https://link0")
        val mockOther = Other(mockOfficialArtwork)
        val mockSprite = Sprite("https://link1","https://link2",mockOther )
        val mockType = Type("type", "url")
        val mockListTypes = listOf(PokeType(1,mockType))
        val mockPokemon = Pokemon(1,"pikachu",1,3,mockSprite,mockListTypes)
        coEvery { getDetailPokemonUseCase(any()) } returns mockPokemon

        //When
        detailPokemonViewModel.getDetailPokemon("pikachu")

        //Then
        coVerify(exactly = 1) {getDetailPokemonUseCase(any()) }
    }

    @Test
    fun `when existing a pokeFavEntity adds a favorite pokemon `() = runTest(){
        //Given
        val mockPokeFavEntity = PokeFavEntity(1,"pikachu",1,3,"https://link0")

        //When
        detailPokemonViewModel.addFavoritePokemon(mockPokeFavEntity)

        //Then
        coVerify(exactly = 1) { addFavoritePokemonUseCase(any()) }
    }


    @Test
    fun `when existing a pokeFavEntity removes a favorite pokemon `() = runTest(){
        //Given
        val mockPokeFavEntity = PokeFavEntity(1,"pikachu",1,3,"https://link0")

        //When
        detailPokemonViewModel.removeFavoritePokemon(mockPokeFavEntity)

        //Then
        coVerify(exactly = 1) { removeFavoritePokemonUseCase(any()) }
    }


    @Test
    fun `when existing data returs a favorite pokemon list `() = runTest(){
        //Given
        val pokeFavList: LiveData<PokeFavEntity> = listOf(PokeFavEntity(0,
            "pikachu",
            1,
            3,
            "https://url1"),PokeFavEntity
            (1,
            "charmander",
            1,
            2,
            "https://url2")).asFlow().asLiveData()


        //When
        detailPokemonViewModel.getAllFavoritePokemons()

        //Then
        coVerify(exactly = 1) { detailPokemonViewModel.getAllFavoritePokemons() }
    }




}