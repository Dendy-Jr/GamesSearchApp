package com.dendi.android.gamessearchapp.presentation.core

import android.os.Bundle
import com.dendi.android.gamessearchapp.databinding.ActivityMainBinding
import com.dendi.android.gamessearchapp.presentation.games.GamesFragment

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            startFragment(GamesFragment())
        }
    }
}