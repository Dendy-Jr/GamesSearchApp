package com.dendi.android.gamessearchapp.presentation.games

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import com.dendi.android.gamessearchapp.R
import com.dendi.android.gamessearchapp.core.Retry
import com.dendi.android.gamessearchapp.databinding.FragmentGamesBinding
import com.dendi.android.gamessearchapp.presentation.core.BaseFragment
import com.dendi.android.gamessearchapp.presentation.core.ClickListener
import com.dendi.android.gamessearchapp.presentation.core.navigator
import com.dendi.android.gamessearchapp.presentation.detail.DetailFragment

/**
 * @author Dendy-Jr on 04.11.2021
 * olehvynnytskyi@gmail.com
 */
class GamesFragment : BaseFragment<GamesViewModel>(), SearchView.OnQueryTextListener {

    override fun setRecyclerView() = binding.rvGames
    override fun viewModelClass() = GamesViewModel::class.java
    private var _binding: FragmentGamesBinding? = null
    private val binding get() = _binding!!
    private lateinit var gamesAdapter: GamesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGamesBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbarList)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gamesAdapter = GamesAdapter(
            object : Retry {
                override fun tryAgain() {
                    viewModel.fetchGames()
                }
            },
            object : ClickListener<Int> {
                override fun click(item: Int) {
                    val fragment = DetailFragment().apply {
                        arguments = bundleOf("id" to item)
                    }
                    navigator().launchFragment(this@GamesFragment, fragment)
                }
            })

        setAdapter(gamesAdapter)
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.observe(this, { games ->
            games.map(gamesAdapter)
            scrollTo()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.games_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?) = true

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            searchGame(query)
        }
        return true
    }

    private fun searchGame(query: String) {
        val searchQuery = "%$query%"
        viewModel.searchGame(searchQuery).observe(this, { games ->
            games.map(gamesAdapter)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}