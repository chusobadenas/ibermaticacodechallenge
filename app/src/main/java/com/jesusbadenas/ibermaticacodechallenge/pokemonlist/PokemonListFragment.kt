package com.jesusbadenas.ibermaticacodechallenge.pokemonlist

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.jesusbadenas.ibermaticacodechallenge.R
import com.jesusbadenas.ibermaticacodechallenge.common.BaseMvpFragment
import com.jesusbadenas.ibermaticacodechallenge.entities.Pokemon
import javax.inject.Inject

/**
 * Fragment that shows a list of Pokemon.
 */
class PokemonListFragment : BaseMvpFragment(), PokemonListMvpView {

    @Inject
    lateinit var pokemonListPresenter: PokemonListPresenter
    @Inject
    lateinit var pokemonAdapter: PokemonAdapter

    @BindView(R.id.rv_pokemons)
    lateinit var viewPokemons: RecyclerView
    @BindView(R.id.rl_progress)
    lateinit var viewProgress: RelativeLayout
    @BindView(R.id.rl_retry)
    lateinit var viewRetry: RelativeLayout
    @BindView(R.id.swipe_container)
    lateinit var swipeRefresh: SwipeRefreshLayout

    private var pokemonListListener: PokemonListListener? = null
    private var unbinder: Unbinder? = null

    companion object {
        fun newInstance(): PokemonListFragment {
            return PokemonListFragment()
        }
    }

    private val onItemClickListener = object : PokemonAdapter.OnItemClickListener {
        override fun onPokemonItemClicked(pokemon: Pokemon) {
            pokemonListPresenter.onPokemonClicked(pokemon)
        }
    }

    interface PokemonListListener {
        fun onPokemonClicked(pokemon: Pokemon)
    }

    override fun onAttachToContext(context: Context) {
        if (context is PokemonListListener) {
            pokemonListListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentView = inflater.inflate(R.layout.fragment_pokemon_list, container, false)
        unbinder = ButterKnife.bind(this, fragmentView)
        pokemonListPresenter.attachView(this)
        setupRecyclerView()
        return fragmentView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_search -> {
            // TODO
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()
        loadPokemonList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewPokemons.adapter = null
        unbinder?.unbind()
    }

    override fun onDestroy() {
        super.onDestroy()
        pokemonListPresenter.detachView()
    }

    override fun showLoading() {
        swipeRefresh.visibility = View.GONE
        swipeRefresh.isRefreshing = true
        viewProgress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        swipeRefresh.visibility = View.VISIBLE
        swipeRefresh.isRefreshing = false
        viewProgress.visibility = View.GONE
    }

    override fun showRetry() {
        viewRetry.visibility = View.VISIBLE
    }

    override fun hideRetry() {
        viewRetry.visibility = View.GONE
    }

    override fun showPokemonList(pokemons: List<Pokemon>) {
        pokemonAdapter.setPokemons(pokemons)
    }

    override fun viewPokemon(pokemon: Pokemon) {
        pokemonListListener?.onPokemonClicked(pokemon)
    }

    private fun setupRecyclerView() {
        pokemonAdapter.setOnItemClickListener(onItemClickListener)
        viewPokemons.layoutManager = PokemonLayoutManager(context())
        viewPokemons.adapter = pokemonAdapter

        swipeRefresh.setColorSchemeResources(R.color.primary)
        swipeRefresh.setOnRefreshListener {
            loadPokemonList()
        }
    }

    private fun loadPokemonList() {
        pokemonListPresenter.initialize()
    }

    @OnClick(R.id.bt_retry)
    fun onButtonRetryClick() {
        loadPokemonList()
    }
}
