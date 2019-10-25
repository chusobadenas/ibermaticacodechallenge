package com.jesusbadenas.ibermaticacodechallenge.di.components

import android.app.Activity
import com.jesusbadenas.ibermaticacodechallenge.di.PerActivity
import com.jesusbadenas.ibermaticacodechallenge.di.modules.ActivityModule
import dagger.Component

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 * [PerActivity]
 */
@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun activity(): Activity
}
