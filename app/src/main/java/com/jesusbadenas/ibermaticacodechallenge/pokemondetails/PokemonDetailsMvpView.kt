package com.jesusbadenas.ibermaticacodechallenge.pokemondetails

import com.jesusbadenas.ibermaticacodechallenge.common.MvpView
import com.jesusbadenas.ibermaticacodechallenge.entities.Pokemon

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a pokemon profile.
 */
interface PokemonDetailsMvpView : MvpView {

    fun showPokemonDetails(pokemon: Pokemon)
}
