package com.dendi.android.gamessearchapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dendi.android.gamessearchapp.R

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
class GameAdapter(private val retry: Retry) :
    RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private val games = ArrayList<GameUi>()

    fun update(new: List<GameUi>) {
        val diffCallback = DiffUtilCallback(games, new)
        val result = DiffUtil.calculateDiff(diffCallback)
        games.clear()
        games.addAll(new)
        result.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int) = when (games[position]) {
        is GameUi.Base -> 0
        is GameUi.Fail -> 1
        is GameUi.Progress -> 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> GameViewHolder.Base(R.layout.game_layout.makeView(parent))
        1 -> GameViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
        else -> GameViewHolder.FullScreenProgress(R.layout.progress_fullscreen.makeView(parent))

    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount() = games.size


    abstract class GameViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        open fun bind(game: GameUi) {}

        class FullScreenProgress(view: View) : GameViewHolder(view)

        class Base(view: View) : GameViewHolder(view) {
            private val titleView = itemView.findViewById<TextView>(R.id.title)
            private val thumbnailView = itemView.findViewById<ImageView>(R.id.thumbnail_iv)
            override fun bind(game: GameUi) {
                game.map(object : UiResultMapper {
                    override fun map(
                        id: Int,
                        thumbnail: String,
                        title: String,
                    ) {
                        titleView.text = title
                        Glide.with(itemView)
                            .load(thumbnail)
                            .centerCrop()
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(thumbnailView)
                    }

                    override fun map(message: String) {}
                })
            }
        }

        class Fail(view: View, private val retry: Retry) : GameViewHolder(view) {
            private val messageView = itemView.findViewById<TextView>(R.id.message_tv)
            private val buttonView = itemView.findViewById<Button>(R.id.tryAgainButton)
            override fun bind(game: GameUi) {
                game.map(object : UiResultMapper {
                    override fun map(
                        id: Int,
                        thumbnail: String,
                        title: String,
                    ) {
                    }

                    override fun map(message: String) {
                        messageView.text = message
                    }
                })
                buttonView.setOnClickListener {
                    retry.tryAgain()
                }
            }
        }
    }

    interface Retry {
        fun tryAgain()
    }
}

private fun Int.makeView(parent: ViewGroup) =
    LayoutInflater.from(parent.context).inflate(this, parent, false)