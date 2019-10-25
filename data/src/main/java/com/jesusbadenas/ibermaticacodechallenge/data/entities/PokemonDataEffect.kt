package com.jesusbadenas.ibermaticacodechallenge.data.entities

import com.google.gson.annotations.SerializedName

data class PokemonDataEffect(
    @SerializedName("effect") val effect: String,
    @SerializedName("short_effect") val shortEffect: String
)
