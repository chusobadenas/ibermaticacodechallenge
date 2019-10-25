package com.jesusbadenas.ibermaticacodechallenge.pokemondetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.jesusbadenas.ibermaticacodechallenge.R
import com.jesusbadenas.ibermaticacodechallenge.common.BaseMvpFragment
import com.jesusbadenas.ibermaticacodechallenge.common.UIUtils
import com.jesusbadenas.ibermaticacodechallenge.entities.Pokemon
import javax.inject.Inject

/**
 * Fragment that shows details of a certain pokemon.
 */
class PokemonDetailsFragment : BaseMvpFragment(), PokemonDetailsMvpView {

    @Inject
    lateinit var pokemonDetailsPresenter: PokemonDetailsPresenter

    @BindView(R.id.iv_cover)
    lateinit var imageViewCover: ImageView
    @BindView(R.id.tv_name)
    lateinit var textViewName: TextView
    @BindView(R.id.rl_progress)
    lateinit var viewProgress: RelativeLayout
    @BindView(R.id.rl_retry)
    lateinit var viewRetry: RelativeLayout
    @BindView(R.id.pokemon_detail_view)
    lateinit var viewPokemonDetail: LinearLayout

    private var pokemonDetailsListener: PokemonDetailsListener? = null
    private var unbinder: Unbinder? = null

    companion object {
        fun newInstance(): PokemonDetailsFragment {
            return PokemonDetailsFragment()
        }
    }

    interface PokemonDetailsListener {
        fun setToolbarTitle(title: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentView = inflater.inflate(R.layout.fragment_pokemon_details, container, false)
        unbinder = ButterKnife.bind(this, fragmentView)
        pokemonDetailsPresenter.attachView(this)
        return fragmentView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder?.unbind()
    }

    override fun onAttachToContext(context: Context) {
        if (context is PokemonDetailsListener) {
            pokemonDetailsListener = context
        }
    }

    override fun onStart() {
        super.onStart()
        loadPokemonDetails()
    }

    override fun onDestroy() {
        super.onDestroy()
        pokemonDetailsPresenter.detachView()
    }

    override fun showPokemonDetails(pokemon: Pokemon) {
        UIUtils.loadImageUrl(
            context(), imageViewCover,
            "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/pikachu-imagen-1557540540.jpeg"
        )
        textViewName.text = pokemon.name
        pokemonDetailsListener?.setToolbarTitle(pokemon.name)
    }

    override fun showLoading() {
        viewPokemonDetail.visibility = View.GONE
        viewProgress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        viewPokemonDetail.visibility = View.VISIBLE
        viewProgress.visibility = View.GONE
    }

    override fun showRetry() {
        viewRetry.visibility = View.VISIBLE
    }

    override fun hideRetry() {
        viewRetry.visibility = View.GONE
    }

    private fun loadPokemonDetails() {
        val pokemonId = (activity as PokemonDetailsActivity).pokemonId
        pokemonDetailsPresenter.initialize(pokemonId)
    }

    @OnClick(R.id.bt_retry)
    fun onButtonRetryClick() {
        loadPokemonDetails()
    }
}
