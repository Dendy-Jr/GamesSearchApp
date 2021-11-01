package com.dendi.android.gamessearchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dendi.android.gamessearchapp.core.GamesApp
import com.dendi.android.gamessearchapp.databinding.ActivityMainBinding
import com.dendi.android.gamessearchapp.presentation.GameAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val gameViewModel = (application as GamesApp).gameViewModel
        val gameAdapter = GameAdapter(
            object : GameAdapter.Retry {
                override fun tryAgain() {
                    gameViewModel.fetchGames()
                }
            }
        )
        val recyclerView = binding.recyclerView

        recyclerView.adapter = gameAdapter
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        gameViewModel.observe(this, {
            gameAdapter.update(it)
        })
        gameViewModel.fetchGames()
    }
}