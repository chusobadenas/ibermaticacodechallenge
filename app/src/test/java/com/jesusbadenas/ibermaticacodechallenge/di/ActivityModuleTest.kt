package com.jesusbadenas.ibermaticacodechallenge.di

import android.app.Activity
import com.jesusbadenas.ibermaticacodechallenge.di.modules.ActivityModule
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class ActivityModuleTest {

    lateinit var activityModule: ActivityModule

    @MockK
    lateinit var mockActivity: Activity

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        activityModule = ActivityModule(mockActivity)
    }

    @Test
    fun testGetActivitySuccess() {
        val activity = activityModule.activity()
        assertNotNull(activity)
        assertEquals(activity, mockActivity)
    }
}
