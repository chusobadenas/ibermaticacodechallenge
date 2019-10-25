package com.jesusbadenas.ibermaticacodechallenge.entities.mappers

import com.jesusbadenas.ibermaticacodechallenge.domain.common.Mapper
import com.jesusbadenas.ibermaticacodechallenge.domain.entities.PokemonEntity
import com.jesusbadenas.ibermaticacodechallenge.entities.Pokemon
import javax.inject.Inject

class PokemonEntityMapper
@Inject
constructor() : Mapper<PokemonEntity, Pokemon>() {
    override fun mapFrom(from: PokemonEntity) = Pokemon(
        from.pokemonId,
        from.name
    )

    override fun mapFrom(from: List<PokemonEntity>): List<Pokemon> {
        val pokemonList: ArrayList<Pokemon> = ArrayList()

        if (from.isNotEmpty()) {
            for (pokemonEntity in from) {
                pokemonList.add(mapFrom(pokemonEntity))
            }
        }

        return pokemonList
    }
}
