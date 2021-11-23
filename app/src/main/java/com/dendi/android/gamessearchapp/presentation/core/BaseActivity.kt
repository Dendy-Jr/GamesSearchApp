package com.dendi.android.gamessearchapp.presentation.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.dendi.android.gamessearchapp.R
import com.dendi.android.gamessearchapp.core.GamesApp


/**
 * @author Dendy-Jr on 22.11.2021
 * olehvynnytskyi@gmail.com
 */
abstract class BaseActivity : AppCompatActivity(), Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = ""
    }

    fun <T : ViewModel> viewModel(model: Class<T>, owner: ViewModelStoreOwner) =
        (application as GamesApp).viewModel(model, owner)

    override fun onSupportNavigateUp(): Boolean {
        back()
        return true
    }

    override fun backToHome() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun startFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, fragment)
            .commit()
    }

    override fun launchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    override fun back() {
        onBackPressed()
    }
}