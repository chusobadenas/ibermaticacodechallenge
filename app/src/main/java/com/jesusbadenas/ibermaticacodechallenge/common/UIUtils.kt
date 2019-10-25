package com.jesusbadenas.ibermaticacodechallenge.common

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jesusbadenas.ibermaticacodechallenge.R
import com.jesusbadenas.ibermaticacodechallenge.di.modules.GlideApp

/**
 * UI utilities class
 */
object UIUtils {

    fun loadImageUrl(context: Context, view: ImageView, url: String?) {
        // Configure image
        GlideApp.with(context)
            .load(url)
            .centerCrop()
            .placeholder(R.color.bg_light_grey)
            .error(R.color.bg_light_grey)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}
