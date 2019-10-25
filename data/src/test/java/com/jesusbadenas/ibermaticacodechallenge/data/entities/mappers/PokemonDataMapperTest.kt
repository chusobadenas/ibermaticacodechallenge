package com.jesusbadenas.ibermaticacodechallenge.data.entities.mappers

import com.jesusbadenas.ibermaticacodechallenge.data.entities.PokemonData
import org.junit.Assert.*
import org.junit.Test

class PokemonDataMapperTest {

    companion object {
        private const val NAME = "Pikachu"
        private const val POKEMON_ID = 1
    }

    private val pokemonDataMapper: PokemonDataMapper = PokemonDataMapper()
    private val pokemonData: PokemonData = PokemonData(POKEMON_ID, NAME)

    @Test
    fun testTransformToPokemonEntity() {
        val pokemonEntity = pokemonDataMapper.transform(pokemonData)

        assertEquals(pokemonEntity.name, NAME)
        assertSame(pokemonEntity.pokemonId, POKEMON_ID)
    }

    @Test
    fun testTransformToPokemonEntityCollection() {
        val pokemonDataList = arrayListOf(pokemonData)

        val pokemonEntities = pokemonDataMapper.transform(pokemonDataList)

        assertTrue(pokemonEntities.isNotEmpty())
        assertSame(pokemonEntities.size, 1)

        val pokemonEntity = pokemonEntities[0]
        assertEquals(pokemonEntity.name, NAME)
        assertSame(pokemonEntity.pokemonId, POKEMON_ID)
    }
}
