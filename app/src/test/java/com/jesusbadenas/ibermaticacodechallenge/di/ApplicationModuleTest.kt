package com.jesusbadenas.ibermaticacodechallenge.di

import android.content.Context
import com.jesusbadenas.ibermaticacodechallenge.App
import com.jesusbadenas.ibermaticacodechallenge.data.repositories.PokemonDataRepository
import com.jesusbadenas.ibermaticacodechallenge.di.modules.ApplicationModule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class ApplicationModuleTest {

    lateinit var applicationModule: ApplicationModule

    @MockK
    lateinit var application: App
    @MockK
    lateinit var context: Context
    @MockK
    lateinit var pokemonDataRepository: PokemonDataRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        applicationModule = ApplicationModule()
        every { application.applicationContext } returns context
    }

    @Test
    fun testProvideApplicationContextSuccess() {
        val ctx = applicationModule.provideContext(application)
        assertNotNull(ctx)
        assertEquals(ctx, context)
    }

    @Test
    fun testProvideAPIServiceSuccess() {
        val apiService = applicationModule.provideApiService()
        assertNotNull(apiService)
    }

    @Test
    fun testProvidePokemonRepositorySuccess() {
        assertEquals(
            applicationModule.providePokemonRepository(pokemonDataRepository),
            pokemonDataRepository
        )
    }
}
