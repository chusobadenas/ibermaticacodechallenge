package com.jesusbadenas.ibermaticacodechallenge.pokemondetails

import com.jesusbadenas.ibermaticacodechallenge.domain.interactors.GetPokemonDetails
import com.jesusbadenas.ibermaticacodechallenge.entities.mappers.PokemonEntityMapper
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class PokemonDetailsPresenterTest {

    private lateinit var pokemonDetailsPresenter: PokemonDetailsPresenter

    @MockK(relaxed = true)
    lateinit var getPokemonDetails: GetPokemonDetails
    @MockK(relaxed = true)
    lateinit var pokemonDetailsMvpView: PokemonDetailsMvpView

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        pokemonDetailsPresenter = PokemonDetailsPresenter(getPokemonDetails, PokemonEntityMapper())
        pokemonDetailsPresenter.attachView(pokemonDetailsMvpView)
    }

    @Test
    fun testAttachViewSuccess() {
        assertNotNull(pokemonDetailsPresenter.mvpView)
    }

    @Test
    fun testDetachViewSuccess() {
        pokemonDetailsPresenter.detachView()
        assertNull(pokemonDetailsPresenter.mvpView)
        verify { getPokemonDetails.unsubscribe() }
    }

    @Test
    fun testInitializeSuccess() {
        pokemonDetailsPresenter.initialize(1)
        verify { pokemonDetailsMvpView.hideRetry() }
        verify { pokemonDetailsMvpView.showLoading() }
        verify { getPokemonDetails.execute(any(), eq(hashMapOf("id" to 1))) }
    }
}
