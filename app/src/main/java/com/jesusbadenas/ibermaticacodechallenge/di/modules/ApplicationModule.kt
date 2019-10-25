package com.jesusbadenas.ibermaticacodechallenge.di.modules

import android.content.Context
import com.jesusbadenas.ibermaticacodechallenge.App
import com.jesusbadenas.ibermaticacodechallenge.data.api.APIService
import com.jesusbadenas.ibermaticacodechallenge.data.api.Network
import com.jesusbadenas.ibermaticacodechallenge.data.repositories.PokemonDataRepository
import com.jesusbadenas.ibermaticacodechallenge.di.ApplicationContext
import com.jesusbadenas.ibermaticacodechallenge.domain.repositories.PokemonRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
class ApplicationModule {

    @ApplicationContext
    @Provides
    @Singleton
    internal fun provideContext(application: App): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideApiService(): APIService {
        return Network.newAPIService()
    }

    @Provides
    @Singleton
    internal fun providePokemonRepository(pokemonDataRepository: PokemonDataRepository): PokemonRepository {
        return pokemonDataRepository
    }
}
