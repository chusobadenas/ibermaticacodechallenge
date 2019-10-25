package com.jesusbadenas.ibermaticacodechallenge.di.modules

import com.jesusbadenas.ibermaticacodechallenge.common.BaseActivity
import com.jesusbadenas.ibermaticacodechallenge.di.PerActivity
import com.jesusbadenas.ibermaticacodechallenge.pokemondetails.PokemonDetailsActivity
import com.jesusbadenas.ibermaticacodechallenge.pokemonlist.PokemonListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingsModule {

    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun contributeBaseActivityInjector(): BaseActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [PokemonModule::class])
    internal abstract fun contributePokemonListActivityInjector(): PokemonListActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [PokemonModule::class])
    internal abstract fun contributePokemonDetailsActivityInjector(): PokemonDetailsActivity
}
