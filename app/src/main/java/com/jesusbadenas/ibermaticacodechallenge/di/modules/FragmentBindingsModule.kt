package com.jesusbadenas.ibermaticacodechallenge.di.modules

import com.jesusbadenas.ibermaticacodechallenge.common.BaseFragment
import com.jesusbadenas.ibermaticacodechallenge.di.PerActivity
import com.jesusbadenas.ibermaticacodechallenge.pokemondetails.PokemonDetailsFragment
import com.jesusbadenas.ibermaticacodechallenge.pokemonlist.PokemonListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingsModule {

    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun contributeBaseFragmentInjector(): BaseFragment

    @PerActivity
    @ContributesAndroidInjector(modules = [PokemonModule::class])
    internal abstract fun contributePokemonListFragmentInjector(): PokemonListFragment

    @PerActivity
    @ContributesAndroidInjector(modules = [PokemonModule::class])
    internal abstract fun contributePokemonDetailsFragmentInjector(): PokemonDetailsFragment
}
