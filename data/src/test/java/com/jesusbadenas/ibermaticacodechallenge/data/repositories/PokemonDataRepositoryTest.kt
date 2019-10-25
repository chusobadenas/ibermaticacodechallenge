package com.jesusbadenas.ibermaticacodechallenge.data.repositories

import com.jesusbadenas.ibermaticacodechallenge.data.api.APIService
import com.jesusbadenas.ibermaticacodechallenge.data.entities.PokemonData
import com.jesusbadenas.ibermaticacodechallenge.data.entities.mappers.PokemonDataMapper
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Observable
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PokemonDataRepositoryTest {

    companion object {
        private const val NAME = "Pikachu"
        private const val POKEMON_ID = 1
    }

    private val pokemonData: PokemonData = PokemonData(POKEMON_ID, NAME)
    lateinit var pokemonDataRepository: PokemonDataRepository

    @MockK
    lateinit var apiService: APIService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        pokemonDataRepository = PokemonDataRepository(apiService, PokemonDataMapper())
    }

    @Test
    fun testGetPokemons() {
        // TODO
    }

    @Test
    fun testGetPokemonById() {
        val observablePokemonData = Observable.just<PokemonData>(pokemonData)

        every { apiService.pokemonDataById(POKEMON_ID) } returns observablePokemonData

        val observable = pokemonDataRepository.pokemon(POKEMON_ID)
        val testObserver = observable.test()
        testObserver.assertNoErrors()
        val pokemon = testObserver.values()[0]

        assertSame(pokemon.pokemonId, POKEMON_ID)
    }
}
