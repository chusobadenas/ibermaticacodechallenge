package com.jesusbadenas.ibermaticacodechallenge.data.entities.mappers

import com.jesusbadenas.ibermaticacodechallenge.data.entities.PokemonData
import com.jesusbadenas.ibermaticacodechallenge.data.entities.PokemonDataResult
import com.jesusbadenas.ibermaticacodechallenge.domain.entities.PokemonEntity
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonDataMapper
@Inject
constructor() {
    fun transform(pokemonData: PokemonData): PokemonEntity {
        return PokemonEntity(
            pokemonData.pokemonId,
            pokemonData.name
        )
    }

    fun transform(pokemonDataList: List<PokemonData>): List<PokemonEntity> {
        val pokemonList = ArrayList<PokemonEntity>()
        for (pokemonData in pokemonDataList) {
            val pokemon = transform(pokemonData)
            pokemonList.add(pokemon)
        }
        return pokemonList
    }

    fun transformResult(pokemonDataResult: PokemonDataResult): PokemonEntity {
        val id = pokemonDataResult.url.dropLast(1).takeLast(1).toInt()
        return PokemonEntity(
            id,
            pokemonDataResult.name
        )
    }

    fun transformResultList(pokemonDataResultList: List<PokemonDataResult>): List<PokemonEntity> {
        val pokemonList = ArrayList<PokemonEntity>()
        for (pokemonDataResult in pokemonDataResultList) {
            val pokemon = transformResult(pokemonDataResult)
            pokemonList.add(pokemon)
        }
        return pokemonList
    }
}
