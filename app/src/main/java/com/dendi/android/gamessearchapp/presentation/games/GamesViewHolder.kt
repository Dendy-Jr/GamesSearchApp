package com.dendi.android.gamessearchapp.presentation.games

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dendi.android.gamessearchapp.R
import com.dendi.android.gamessearchapp.core.Retry
import com.dendi.android.gamessearchapp.presentation.core.BaseViewHolder
import com.dendi.android.gamessearchapp.presentation.core.ClickListener

/**
 * @author Dendy-Jr on 10.11.2021
 * olehvynnytskyi@gmail.com
 */
abstract class GamesViewHolder(view: View) : BaseViewHolder<GameUi>(view) {

    class Base(view: View, private val listener: ClickListener<Int>) :
        BaseViewHolder<GameUi>(view) {

        private val titleView = itemView.findViewById<TextView>(R.id.title)
        private val thumbnailView = itemView.findViewById<ImageView>(R.id.thumbnail_iv)
        private val shortDescriptionView = itemView.findViewById<TextView>(R.id.shortDescription_tv)

        override fun bind(item: GameUi) {
            item.map(object : GameUiMapper<Unit> {
                override fun map(
                    id: Int, thumbnail: String, title: String, shortDescription: String,
                ) {
                    titleView.text = title
                    shortDescriptionView.text = shortDescription

                    Glide.with(itemView)
                        .load(thumbnail)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.image_loading)
                        .error(R.drawable.not_found_image)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(thumbnailView)
                }

                override fun map(message: String) = Unit
            })
            itemView.setOnClickListener {
                item.map(listener)
            }
        }
    }

    class Error(view: View, retry: Retry) :
        BaseViewHolder.Fail<GameUi>(view, retry) {
        override fun bind(item: GameUi) {
            item.map(object : GameUiMapper<Unit> {
                override fun map(
                    id: Int,
                    thumbnail: String,
                    title: String,
                    shortDescription: String,
                ) = Unit

                override fun map(message: String) {
                    error.text = message
                }
            })
            button.setOnClickListener {
                retry.tryAgain()
            }
        }
    }
}