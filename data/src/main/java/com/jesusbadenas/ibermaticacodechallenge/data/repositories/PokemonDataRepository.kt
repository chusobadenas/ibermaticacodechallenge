package com.jesusbadenas.ibermaticacodechallenge.data.repositories

import com.jesusbadenas.ibermaticacodechallenge.data.api.APIService
import com.jesusbadenas.ibermaticacodechallenge.data.entities.mappers.PokemonDataMapper
import com.jesusbadenas.ibermaticacodechallenge.domain.entities.PokemonEntity
import com.jesusbadenas.ibermaticacodechallenge.domain.repositories.PokemonRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * [PokemonRepository] for retrieving pokemon data.
 */
@Singleton
class PokemonDataRepository
@Inject
constructor(
    private val apiService: APIService,
    private val pokemonDataMapper: PokemonDataMapper
) : PokemonRepository {

    override fun pokemons(): Observable<List<PokemonEntity>> {
        return apiService.pokemonList()
            .map { pokemonList ->
                pokemonDataMapper.transformResultList(pokemonList.results)
            }
    }

    override fun pokemon(pokemonId: Int): Observable<PokemonEntity> {
        return apiService.pokemonDataById(pokemonId)
            .map { pokemonEntity ->
                pokemonDataMapper.transform(pokemonEntity)
            }
    }
}
