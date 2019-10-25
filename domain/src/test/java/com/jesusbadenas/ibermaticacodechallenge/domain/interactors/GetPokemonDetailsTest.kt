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

class GetPokemonDetailsTest {

    companion object {
        private const val POKEMON_ID = 1
    }

    lateinit var getPokemonDetails: GetPokemonDetails

    @MockK
    lateinit var pokemonRepository: PokemonRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getPokemonDetails = GetPokemonDetails(pokemonRepository)
    }

    @Test
    fun testGetPokemonById() {
        val observable = Observable.empty<PokemonEntity>()
        every { pokemonRepository.pokemon(POKEMON_ID) } returns observable

        val params = hashMapOf("id" to POKEMON_ID)
        getPokemonDetails.create(params)

        verify { pokemonRepository.pokemon(POKEMON_ID) }
    }
}
