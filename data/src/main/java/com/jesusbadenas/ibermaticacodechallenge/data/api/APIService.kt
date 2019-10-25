package com.jesusbadenas.ibermaticacodechallenge.data.api

import com.jesusbadenas.ibermaticacodechallenge.data.entities.PokemonData
import com.jesusbadenas.ibermaticacodechallenge.data.entities.PokemonDataList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * APIService for retrieving data from the network using Retrofit2
 */
interface APIService {

    companion object {
        private const val POKEMON_ID = "pokemonId"
    }

    @GET("/api/v2/pokemon")
    fun pokemonList(): Observable<PokemonDataList>

    @GET("/api/v2/pokemon/{$POKEMON_ID}")
    fun pokemonDataById(@Path(POKEMON_ID) pokemonId: Int): Observable<PokemonData>
}
