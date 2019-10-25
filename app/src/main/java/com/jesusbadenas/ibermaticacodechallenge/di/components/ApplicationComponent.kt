package com.jesusbadenas.ibermaticacodechallenge.di.components

import android.content.Context
import com.jesusbadenas.ibermaticacodechallenge.App
import com.jesusbadenas.ibermaticacodechallenge.data.api.APIService
import com.jesusbadenas.ibermaticacodechallenge.data.repositories.PokemonDataRepository
import com.jesusbadenas.ibermaticacodechallenge.di.ApplicationContext
import com.jesusbadenas.ibermaticacodechallenge.di.modules.ActivityBindingsModule
import com.jesusbadenas.ibermaticacodechallenge.di.modules.ApplicationModule
import com.jesusbadenas.ibermaticacodechallenge.di.modules.FragmentBindingsModule
import com.jesusbadenas.ibermaticacodechallenge.navigation.Navigator
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton
@Component(
    modules = [AndroidInjectionModule::class, ActivityBindingsModule::class,
        FragmentBindingsModule::class, ApplicationModule::class]
)
interface ApplicationComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        fun build(): ApplicationComponent

        @BindsInstance
        fun application(application: App): Builder
    }

    @ApplicationContext
    fun context(): Context

    fun apiService(): APIService

    fun navigator(): Navigator

    fun pokemonDataRepository(): PokemonDataRepository
}
