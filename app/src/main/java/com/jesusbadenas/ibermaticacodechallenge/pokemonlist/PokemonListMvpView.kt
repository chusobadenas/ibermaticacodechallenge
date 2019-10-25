package com.jesusbadenas.ibermaticacodechallenge.pokemonlist

import com.jesusbadenas.ibermaticacodechallenge.common.MvpView
import com.jesusbadenas.ibermaticacodechallenge.entities.Pokemon

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of [Pokemon].
 */
interface PokemonListMvpView : MvpView {

    fun showPokemonList(pokemons: List<Pokemon>)

    fun viewPokemon(pokemon: Pokemon)
}
