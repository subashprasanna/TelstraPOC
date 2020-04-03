package com.cts.telstrapoc.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Binding Adapter helps to create custom attribute
 * image arg - custom attribute can be used in xml to load image using glide framework
 * Ex. app:image = ""
 */

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String?) {
        Glide.with(view)
            .load(url)
            .into(view)

}