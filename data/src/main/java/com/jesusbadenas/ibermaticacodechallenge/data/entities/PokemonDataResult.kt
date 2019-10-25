package com.jesusbadenas.ibermaticacodechallenge.data.entities

import com.google.gson.annotations.SerializedName

data class PokemonDataResult(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)
