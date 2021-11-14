package com.dendi.android.gamessearchapp.presentation.detail

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
sealed class DetailUiState {

    abstract fun map(mapper: DetailCommunication)

    class Base(
        private val detail: DetailUi,
    ) : DetailUiState() {
        override fun map(mapper: DetailCommunication) = mapper.map(detail)
    }
}
