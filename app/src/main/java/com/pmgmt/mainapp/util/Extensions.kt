package com.pmgmt.mainapp.util

import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.pmgmt.mainapp.util.glide.GlideApp

fun ImageView.loadWithGlide(url: String?) {
    GlideApp.with(this.context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade()) // Crossfade animation
        .into(this)
}