package com.pmgmt.mainapp.util.glide

import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

object GlideConfig {
    private const val DEFAULT_CORNER_RADIUS = 80f

    fun getDefaultRequestOptions(): RequestOptions {
        return RequestOptions()
            .centerCrop()
        //.placeholder(R.drawable.placeholder)
        //.error(R.drawable.error_image)
    }
}