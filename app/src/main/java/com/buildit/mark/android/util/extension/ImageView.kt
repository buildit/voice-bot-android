package com.buildit.mark.android.util.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by harshit.laddha on 25/01/2020
 */
internal fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
            .load(url)
            .asBitmap()
            .centerCrop()
            .into(this)
}