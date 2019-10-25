package com.jesusbadenas.ibermaticacodechallenge.domain.interactors

import com.jesusbadenas.ibermaticacodechallenge.domain.entities.PokemonEntity
import com.jesusbadenas.ibermaticacodechallenge.domain.repositories.PokemonRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class GetPokemonListTest {

    lateinit var getPokemonList: GetPokemonList

    @MockK
    lateinit var pokemonRepository: PokemonRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getPokemonList = GetPokemonList(pokemonRepository)
    }

    @Test
    fun testGetPokemons() {
        val observable = Observable.empty<List<PokemonEntity>>()
        every { pokemonRepository.pokemons() } returns observable

        getPokemonList.create(null)

        verify { pokemonRepository.pokemons() }
    }
}
