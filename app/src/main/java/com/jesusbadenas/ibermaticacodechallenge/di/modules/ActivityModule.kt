package com.jesusbadenas.ibermaticacodechallenge.di.modules

import android.app.Activity
import com.jesusbadenas.ibermaticacodechallenge.di.PerActivity
import dagger.Module
import dagger.Provides

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @PerActivity
    internal fun activity(): Activity {
        return activity
    }
}
