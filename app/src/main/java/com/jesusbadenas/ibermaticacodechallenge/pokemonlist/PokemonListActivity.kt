package com.jesusbadenas.ibermaticacodechallenge.pokemonlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.jesusbadenas.ibermaticacodechallenge.R
import com.jesusbadenas.ibermaticacodechallenge.common.BaseActivity
import com.jesusbadenas.ibermaticacodechallenge.entities.Pokemon

/**
 * Activity that shows a list of Pokemon.
 */
class PokemonListActivity : BaseActivity(), PokemonListFragment.PokemonListListener {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, PokemonListActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, PokemonListFragment.newInstance())
        }
    }

    override fun onPokemonClicked(pokemon: Pokemon) {
        navigator.navigateToPokemonDetails(this, pokemon.pokemonId)
    }
}
