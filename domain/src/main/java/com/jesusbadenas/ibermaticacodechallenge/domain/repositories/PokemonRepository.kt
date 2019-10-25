package com.jesusbadenas.ibermaticacodechallenge.domain.repositories

import com.jesusbadenas.ibermaticacodechallenge.domain.entities.PokemonEntity
import io.reactivex.Observable

/**
 * Interface that represents a Repository for getting [PokemonEntity] related data.
 */
interface PokemonRepository {

    fun pokemons(): Observable<List<PokemonEntity>>

    fun pokemon(pokemonId: Int): Observable<PokemonEntity>
}
