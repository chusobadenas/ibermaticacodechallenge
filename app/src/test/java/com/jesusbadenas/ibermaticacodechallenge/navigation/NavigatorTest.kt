package com.jesusbadenas.ibermaticacodechallenge.navigation

import android.content.ActivityNotFoundException
import android.content.Context
import com.jesusbadenas.ibermaticacodechallenge.App
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = App::class, sdk = [27])
class NavigatorTest {

    private val navigator = Navigator()

    @MockK(relaxed = true)
    lateinit var context: Context

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testNavigateToPokemonListSuccess() {
        try {
            navigator.navigateToPokemonList(context)
        } catch (exception: ActivityNotFoundException) {
            fail("PokemonListActivity not found")
        }
    }

    @Test
    fun testNavigateToPokemonDetailsSuccess() {
        try {
            navigator.navigateToPokemonDetails(context, 1)
        } catch (exception: ActivityNotFoundException) {
            fail("PokemonDetailsActivity not found")
        }
    }
}
