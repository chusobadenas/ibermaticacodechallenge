package com.jesusbadenas.ibermaticacodechallenge.domain.interactors

import com.jesusbadenas.ibermaticacodechallenge.domain.common.UseCase
import com.jesusbadenas.ibermaticacodechallenge.domain.entities.PokemonEntity
import com.jesusbadenas.ibermaticacodechallenge.domain.repositories.PokemonRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetPokemonDetails
@Inject
constructor(private val pokemonRepository: PokemonRepository) : UseCase<PokemonEntity>() {

    override fun create(data: Map<String, Any>?): Observable<PokemonEntity> {
        val pokemonId = data?.get("id") as Int
        return pokemonRepository.pokemon(pokemonId)
    }
}
