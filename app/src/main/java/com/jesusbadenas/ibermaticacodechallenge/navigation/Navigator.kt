package com.jesusbadenas.ibermaticacodechallenge.navigation

import android.content.Context
import com.jesusbadenas.ibermaticacodechallenge.pokemondetails.PokemonDetailsActivity
import com.jesusbadenas.ibermaticacodechallenge.pokemonlist.PokemonListActivity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class used to navigate through the application.
 */
@Singleton
class Navigator
@Inject
constructor() {
    fun navigateToPokemonList(context: Context) {
        val intentToLaunch = PokemonListActivity.getCallingIntent(context)
        context.startActivity(intentToLaunch)
    }

    fun navigateToPokemonDetails(context: Context, pokemonId: Int) {
        val intentToLaunch = PokemonDetailsActivity.getCallingIntent(context, pokemonId)
        context.startActivity(intentToLaunch)
    }
}
