package com.jesusbadenas.ibermaticacodechallenge.di

import com.jesusbadenas.ibermaticacodechallenge.data.repositories.PokemonDataRepository
import com.jesusbadenas.ibermaticacodechallenge.di.modules.PokemonModule
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class PokemonModuleTest {

    private lateinit var pokemonModule: PokemonModule

    @MockK
    lateinit var pokemonDataRepository: PokemonDataRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        pokemonModule = PokemonModule()
    }

    @Test
    fun testProvideGetPokemonList() {
        assertNotNull(pokemonModule.provideGetPokemonListUseCase(pokemonDataRepository))
    }

    @Test
    fun testProvideGetPokemonDetailsUseCase() {
        assertNotNull(pokemonModule.provideGetPokemonDetailsUseCase(pokemonDataRepository))
    }
}
