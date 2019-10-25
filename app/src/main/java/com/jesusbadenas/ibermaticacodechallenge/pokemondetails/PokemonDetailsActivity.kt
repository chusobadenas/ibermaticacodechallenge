package com.jesusbadenas.ibermaticacodechallenge.pokemondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.jesusbadenas.ibermaticacodechallenge.R
import com.jesusbadenas.ibermaticacodechallenge.common.BaseActivity

/**
 * Activity that shows details of a certain pokemon.
 */
class PokemonDetailsActivity : BaseActivity(), PokemonDetailsFragment.PokemonDetailsListener {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    var pokemonId: Int = -1

    companion object {
        private const val INTENT_PARAM_POKEMON_ID = "INTENT_PARAM_POKEMON_ID"

        fun getCallingIntent(context: Context, pokemonId: Int): Intent {
            val callingIntent = Intent(context, PokemonDetailsActivity::class.java)
            callingIntent.putExtra(INTENT_PARAM_POKEMON_ID, pokemonId)
            return callingIntent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        ButterKnife.bind(this)
        setupToolbar()
        if (savedInstanceState == null) {
            pokemonId = intent.getIntExtra(INTENT_PARAM_POKEMON_ID, -1)
            addFragment(R.id.fragmentContainer, PokemonDetailsFragment.newInstance())
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun setToolbarTitle(title: String) {
        toolbar.title = title
    }
}
