package com.buildit.mark.android.util.extension

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget



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

internal fun ImageView.loadCircularImage(url: String, size: Int) {
    Glide.with(this.context)
            .load(url)
            .asBitmap()
            .centerCrop()
            .into(object: BitmapImageViewTarget(this) {
                override fun setResource(resource: Bitmap?) {
                    resource?. let {
                        val drawable = RoundedBitmapDrawableFactory.create(
                                this@loadCircularImage.context.resources,
                                Bitmap.createScaledBitmap(resource, size, size, false))
                        drawable.isCircular = true
                        this@loadCircularImage.setImageDrawable(drawable)
                    }
                }
            })
}

internal fun ImageView.setCircularDrawable(imageResource: Drawable?, size: Int) {
    imageResource?. let {
        Glide.with(this.context)
            .load(it)
            .asBitmap()
            .centerCrop()
            .into(object: BitmapImageViewTarget(this) {
                override fun setResource(resource: Bitmap?) {
                    resource?. let {
                        val drawable = RoundedBitmapDrawableFactory.create(
                                this@setCircularDrawable.context.resources,
                                Bitmap.createScaledBitmap(resource, size, size, false))
                        drawable.isCircular = true
                        this@setCircularDrawable.setImageDrawable(drawable)
                    }
                }
            })
    }
}
