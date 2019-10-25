package com.jesusbadenas.ibermaticacodechallenge.pokemondetails

import com.jesusbadenas.ibermaticacodechallenge.common.BasePresenter
import com.jesusbadenas.ibermaticacodechallenge.di.PerActivity
import com.jesusbadenas.ibermaticacodechallenge.domain.common.DefaultSubscriber
import com.jesusbadenas.ibermaticacodechallenge.domain.common.UseCase
import com.jesusbadenas.ibermaticacodechallenge.domain.entities.PokemonEntity
import com.jesusbadenas.ibermaticacodechallenge.entities.mappers.PokemonEntityMapper
import javax.inject.Inject

/**
 * [BasePresenter] that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
class PokemonDetailsPresenter
@Inject
constructor(
    private val getPokemonDetailsUseCase: UseCase<PokemonEntity>,
    private val pokemonEntityMapper: PokemonEntityMapper
) : BasePresenter<PokemonDetailsMvpView>() {

    fun initialize(pokemonId: Int) {
        checkViewAttached()
        loadPokemonDetails(pokemonId)
    }

    override fun detachView() {
        super.detachView()
        getPokemonDetailsUseCase.unsubscribe()
    }

    private fun loadPokemonDetails(pokemonId: Int) {
        mvpView?.hideRetry()
        mvpView?.showLoading()
        getPokemonDetails(pokemonId)
    }

    private fun showPokemonDetailsInView(pokemonEntity: PokemonEntity) {
        val pokemon = pokemonEntityMapper.mapFrom(pokemonEntity)
        mvpView?.showPokemonDetails(pokemon)
    }

    private fun getPokemonDetails(pokemonId: Int) {
        val params: Map<String, Int> = hashMapOf("id" to pokemonId)
        getPokemonDetailsUseCase.execute(PokemonDetailsSubscriber(), params)
    }

    private inner class PokemonDetailsSubscriber : DefaultSubscriber<PokemonEntity>() {

        override fun onError(throwable: Throwable) {
            mvpView?.hideLoading()
            showErrorMessage(throwable, "Error loading pokemon details", null)
            mvpView?.showRetry()
        }

        override fun onNext(pokemon: PokemonEntity) {
            mvpView?.hideLoading()
            showPokemonDetailsInView(pokemon)
        }
    }
}
