package com.jesusbadenas.ibermaticacodechallenge.pokemonlist

import com.jesusbadenas.ibermaticacodechallenge.common.BasePresenter
import com.jesusbadenas.ibermaticacodechallenge.di.PerActivity
import com.jesusbadenas.ibermaticacodechallenge.domain.common.DefaultSubscriber
import com.jesusbadenas.ibermaticacodechallenge.domain.common.UseCase
import com.jesusbadenas.ibermaticacodechallenge.domain.entities.PokemonEntity
import com.jesusbadenas.ibermaticacodechallenge.entities.Pokemon
import com.jesusbadenas.ibermaticacodechallenge.entities.mappers.PokemonEntityMapper
import javax.inject.Inject

@PerActivity
class PokemonListPresenter
@Inject
constructor(
    private val getPokemonListUseCase: UseCase<List<PokemonEntity>>,
    private val pokemonEntityMapper: PokemonEntityMapper
) : BasePresenter<PokemonListMvpView>() {

    fun initialize() {
        checkViewAttached()
        loadPokemonList()
    }

    override fun detachView() {
        super.detachView()
        getPokemonListUseCase.unsubscribe()
    }

    private fun loadPokemonList() {
        mvpView?.hideRetry()
        mvpView?.showLoading()
        getPokemonList()
    }

    fun onPokemonClicked(pokemon: Pokemon) {
        mvpView?.viewPokemon(pokemon)
    }

    private fun showPokemonCollectionInView(pokemonEntities: List<PokemonEntity>) {
        val pokemonList = pokemonEntityMapper.mapFrom(pokemonEntities)
        mvpView?.showPokemonList(pokemonList)
    }

    private fun getPokemonList() {
        getPokemonListUseCase.execute(PokemonListSubscriber(), null)
    }

    private inner class PokemonListSubscriber : DefaultSubscriber<List<PokemonEntity>>() {

        override fun onError(throwable: Throwable) {
            mvpView?.hideLoading()
            showErrorMessage(throwable, "Error loading pokemon list", null)
            mvpView?.showRetry()
        }

        override fun onNext(pokemons: List<PokemonEntity>) {
            mvpView?.hideLoading()
            showPokemonCollectionInView(pokemons)
        }
    }
}
