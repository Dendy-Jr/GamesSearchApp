package com.dendi.android.gamessearchapp.presentation.detail

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dendi.android.gamessearchapp.R
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.presentation.core.BaseViewHolder

/**
 * @author Dendy-Jr on 10.11.2021
 * olehvynnytskyi@gmail.com
 */
abstract class ScreenshotViewHolder(view: View) : BaseViewHolder<ScreenshotUi>(view) {

    class Base(view: View) :
        BaseViewHolder<ScreenshotUi>(view) {
        private val screenshot: ImageView = itemView.findViewById(R.id.screenshot)
        override fun bind(item: ScreenshotUi) {
            item.map(object : Abstract.ScreenshotMapper<Unit> {
                override fun map(id: Int, image: String) {
                    Glide.with(itemView)
                        .load(image)
                        .centerCrop()
                        .placeholder(R.drawable.image_loading)
                        .error(R.drawable.not_found_image)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(screenshot)
                }
            })
        }
    }
}