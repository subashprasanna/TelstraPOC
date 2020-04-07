package com.cts.telstrapoc.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * Binding Adapter helps to create custom attribute
 * image arg - custom attribute can be used in xml to load image using glide framework
 * Ex. app:image = ""
 */

@BindingAdapter("image")
public fun loadImage(view: ImageView, url: String?) {
    // Add below commented lines if you do not want to cache image
    // .apply(RequestOptions.skipMemoryCacheOf(true))
    // .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))

    // set diskCacheStrategy if you want to cache image
    // DiskCacheStrategy.RESOURCE = often and resize image caching mechanism. Suits our requirement.
        Glide.with(view)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(view)

}