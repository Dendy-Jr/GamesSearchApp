package com.dendi.android.gamessearchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dendi.android.gamessearchapp.core.GamesApp
import com.dendi.android.gamessearchapp.databinding.ActivityMainBinding
import com.dendi.android.gamessearchapp.presentation.GameAdapter
import com.dendi.android.gamessearchapp.presentation.GameViewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var gameAdapter: GameAdapter
    private val gameViewModel: GameViewModel by lazy {
        (application as GamesApp).gameViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gameAdapter = GameAdapter(
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.games_menu, menu)

        val search = menu?.findItem(R.id.action_search)
        val searchView = search?.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(query: String?)= true


    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            searchGame(query)
        }
        return true
    }

    private fun searchGame(searchQuery: String) {
        val search = "%$searchQuery%"
        gameViewModel.searchGame(search).observe(this, { games ->
            gameAdapter.update(games)
        })
    }
}