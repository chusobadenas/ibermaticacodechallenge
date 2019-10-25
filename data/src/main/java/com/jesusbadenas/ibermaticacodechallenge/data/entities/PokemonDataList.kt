package com.jesusbadenas.ibermaticacodechallenge.data.entities

import com.google.gson.annotations.SerializedName

data class PokemonDataList(
    @SerializedName("count") val count: Int,
    @SerializedName("results") val results: List<PokemonDataResult>
)
