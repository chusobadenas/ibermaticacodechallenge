package com.jesusbadenas.ibermaticacodechallenge.di.modules

import com.jesusbadenas.ibermaticacodechallenge.data.repositories.PokemonDataRepository
import com.jesusbadenas.ibermaticacodechallenge.di.PerActivity
import com.jesusbadenas.ibermaticacodechallenge.domain.common.UseCase
import com.jesusbadenas.ibermaticacodechallenge.domain.entities.PokemonEntity
import com.jesusbadenas.ibermaticacodechallenge.domain.interactors.GetPokemonDetails
import com.jesusbadenas.ibermaticacodechallenge.domain.interactors.GetPokemonList
import dagger.Module
import dagger.Provides

/**
 * Dagger module that provides pokemon related collaborators.
 */
@Module
class PokemonModule {

    @Provides
    @PerActivity
    internal fun provideGetPokemonListUseCase(pokemonDataRepository: PokemonDataRepository)
            : UseCase<List<PokemonEntity>> {
        return GetPokemonList(pokemonDataRepository)
    }

    @Provides
    @PerActivity
    internal fun provideGetPokemonDetailsUseCase(pokemonDataRepository: PokemonDataRepository)
            : UseCase<PokemonEntity> {
        return GetPokemonDetails(pokemonDataRepository)
    }
}
