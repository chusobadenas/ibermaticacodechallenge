package com.jesusbadenas.ibermaticacodechallenge.pokemonlist

import com.jesusbadenas.ibermaticacodechallenge.domain.interactors.GetPokemonList
import com.jesusbadenas.ibermaticacodechallenge.entities.Pokemon
import com.jesusbadenas.ibermaticacodechallenge.entities.mappers.PokemonEntityMapper
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class PokemonDataListPresenterTest {

    private lateinit var pokemonListPresenter: PokemonListPresenter

    @MockK(relaxed = true)
    lateinit var getPokemonList: GetPokemonList
    @MockK(relaxed = true)
    lateinit var pokemonListMvpView: PokemonListMvpView

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        pokemonListPresenter = PokemonListPresenter(getPokemonList, PokemonEntityMapper())
        pokemonListPresenter.attachView(pokemonListMvpView)
    }

    @Test
    fun testAttachViewSuccess() {
        assertNotNull(pokemonListPresenter.mvpView)
    }

    @Test
    fun testDetachViewSuccess() {
        pokemonListPresenter.detachView()
        assertNull(pokemonListPresenter.mvpView)
        verify { getPokemonList.unsubscribe() }
    }

    @Test
    fun testInitializeSuccess() {
        pokemonListPresenter.initialize()
        verify { pokemonListMvpView.hideRetry() }
        verify { pokemonListMvpView.showLoading() }
        verify { getPokemonList.execute(any(), null) }
    }

    @Test
    fun testOnPokemonClickedSuccess() {
        val pokemon = Pokemon(1, "Pikachu")
        pokemonListPresenter.onPokemonClicked(pokemon)
        verify { pokemonListMvpView.viewPokemon(pokemon) }
    }
}
