package com.dendi.android.gamessearchapp.presentation.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dendi.android.gamessearchapp.core.Retry
import com.dendi.android.gamessearchapp.databinding.FailFullscreenBinding
import com.dendi.android.gamessearchapp.databinding.ProgressFullscreenBinding

/**
 * @author Dendy-Jr on 10.11.2021
 * olehvynnytskyi@gmail.com
 */
abstract class BaseViewHolder<E>(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(item: E) {}

    class FullScreenProgress<E>(binding: ProgressFullscreenBinding) :
        BaseViewHolder<E>(binding.root)

    abstract class Error<E>(binding: FailFullscreenBinding, protected val retry: Retry) :
        BaseViewHolder<E>(binding.root) {
        protected val error = binding.messageTv
        protected val button = binding.tryAgainButton
    }
}