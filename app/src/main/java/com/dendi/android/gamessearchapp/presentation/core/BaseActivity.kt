package com.dendi.android.gamessearchapp.presentation.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.dendi.android.gamessearchapp.core.GamesApp


/**
 * @author Dendy-Jr on 22.11.2021
 * olehvynnytskyi@gmail.com
 */
abstract class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = ""
    }

    fun <T : ViewModel> viewModel(model: Class<T>, owner: ViewModelStoreOwner) =
        (application as GamesApp).viewModel(model, owner)
}