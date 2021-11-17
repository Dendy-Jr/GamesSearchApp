package com.dendi.android.gamessearchapp.presentation.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dendi.android.gamessearchapp.R
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.databinding.FragmentDetailBinding
import com.dendi.android.gamessearchapp.presentation.core.BaseFragment
import com.dendi.android.gamessearchapp.presentation.favorites.FavoriteUi
import com.google.android.material.snackbar.Snackbar

/**
 * @author Dendy-Jr on 04.11.2021
 * olehvynnytskyi@gmail.com
 */
class DetailFragment : BaseFragment<DetailViewModel>() {

    override fun setRecyclerView() = binding.rvScreenshot
    override fun viewModelClass() = DetailViewModel::class.java
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var screenshotAdapter: ScreenshotsAdapter

    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.rotate_open_anim
        )
    }

    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.rotate_close_anim
        )
    }

    private val fromButton: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.from_bottom_anim
        )
    }

    private val toButton: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.to_bottom_anim
        )
    }

    private var clicked = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbarDetail)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        screenshotAdapter = ScreenshotsAdapter()
        var id = 0
        val bundle: Bundle? = this.arguments
        if (arguments != null) {
            val detailId = bundle!!.getInt("id")
            id = detailId
        }

        setupObserve()
        viewModel.fetchDetail(id)

        binding.fabAction.setOnClickListener {
            onAddButtonClick()
        }
    }

    private fun setupObserve() {
        viewModel.observe(this, { detail ->
            detail.map(object : DetailUiMapper<Unit> {
                override fun map(
                    description: String,
                    developer: String,
                    freetogameProfileUrl: String,
                    gameUrl: String,
                    genre: String,
                    id: Int,
                    systemRequirements: SystemRequirementsUi.Base,
                    platform: String,
                    publisher: String,
                    releaseDate: String,
                    screenshots: List<ScreenshotUi.Base>,
                    shortDescription: String,
                    status: String,
                    thumbnail: String,
                    title: String,
                ) {
                    with(binding) {
                        toolbarDetailTitle.text = title

                        Glide.with(requireActivity())
                            .load(thumbnail)
                            .centerCrop()
                            .placeholder(R.drawable.image_loading)
                            .error(R.drawable.not_found_image)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(ivThumbnail)

                        setAdapter(screenshotAdapter)
                        ScreenshotsUi.Base(screenshots).map(screenshotAdapter)

                        addGameToFavorite(
                            FavoriteUi.Base(
                                id,
                                thumbnail,
                                title,
                                platform,
                                developer
                            ), title
                        )
                        deleteGameFromFavorite(
                            FavoriteUi.Base(
                                id,
                                thumbnail,
                                title,
                                platform,
                                developer
                            ), title
                        )

                        descriptionTextView.text = description
                        tvStrategy.text = genre
                        tvPlatform.text = platform
                        tvPublisher.text = publisher
                        tvDeveloper.text = developer
                        tvReleaseDate.text = releaseDate

                        systemRequirements.map(object : Abstract.SystemRequirementsMapper<Unit> {
                            override fun map(
                                id: Int,
                                graphics: String,
                                memory: String,
                                os: String,
                                processor: String,
                                storage: String,
                            ) {
                                tvOs.text = os
                                tvProcessor.text = processor
                                tvMemory.text = memory
                                tvGraphics.text = graphics
                                tvStorage.text = storage

                            }
                        })
                    }
                }

                override fun map(message: String) = Unit
            })
        })
    }

    private fun addGameToFavorite(game: FavoriteUi.Base, title: String) {
        with(binding) {
            fabAddFavorite.setOnClickListener {
                viewModel.saveToFavorite(game)
                Snackbar.make(it, "$title added to favorites", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteGameFromFavorite(game: FavoriteUi.Base, title: String) {
        with(binding) {
            fabDeleteFavorite.setOnClickListener {
                viewModel.deleteFromFavorite(game)
                Snackbar.make(it, "$title deleted from favorites", Snackbar.LENGTH_SHORT).show()

            }
        }
    }

    private fun onAddButtonClick() {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked: Boolean) {
        with(binding) {
            if (!clicked) {
                fabAddFavorite.startAnimation(fromButton)
                fabDeleteFavorite.startAnimation(fromButton)
                fabAction.startAnimation(rotateOpen)
            } else {
                fabAddFavorite.startAnimation(toButton)
                fabDeleteFavorite.startAnimation(toButton)
                fabAction.startAnimation(rotateClose)
            }
        }
    }

    private fun setVisibility(clicked: Boolean) {
        with(binding) {
            if (!clicked) {
                fabAddFavorite.visibility = View.VISIBLE
                fabDeleteFavorite.visibility = View.VISIBLE
            } else {
                fabAddFavorite.visibility = View.INVISIBLE
                fabDeleteFavorite.visibility = View.INVISIBLE
            }
        }
    }

    private fun setClickable(clicked: Boolean) {
        with(binding) {
            if (!clicked) {
                fabAddFavorite.isClickable = true
                fabDeleteFavorite.isClickable = true
            } else {
                fabAddFavorite.isClickable = false
                fabDeleteFavorite.isClickable = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}







