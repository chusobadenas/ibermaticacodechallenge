package com.jesusbadenas.ibermaticacodechallenge.data.entities

import com.google.gson.annotations.SerializedName

/**
 * Pokemon used in the data layer.
 */
data class PokemonData(
    @SerializedName("id") val pokemonId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("effect_entries") var effects: List<PokemonDataEffect> = emptyList()
)
