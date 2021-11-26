package com.dendi.android.gamessearchapp.presentation.detail


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dendi.android.gamessearchapp.R
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.core.Constants.ID
import com.dendi.android.gamessearchapp.databinding.FragmentDetailBinding
import com.dendi.android.gamessearchapp.presentation.core.BaseFragment
import com.dendi.android.gamessearchapp.presentation.favorites.FavoriteUi
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * @author Dendy-Jr on 04.11.2021
 * olehvynnytskyi@gmail.com
 */
class DetailFragment :
    BaseFragment<DetailViewModel, FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    override fun setRecyclerView() = viewBinding.rvScreenshot
    override fun viewModelClass() = DetailViewModel::class.java
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.setSupportActionBar(viewBinding.toolbarDetail)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        screenshotAdapter = ScreenshotsAdapter()
        var id = 0
        val bundle: Bundle? = this.arguments
        if (arguments != null) {
            val detailId = bundle!!.getInt(ID)
            id = detailId
        }

        setupObserve()
        viewModel.fetchDetail(id)

        viewBinding.fabAction.setOnClickListener {
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

                    setAdapter(screenshotAdapter)
                    ScreenshotsUi.Base(screenshots).map(screenshotAdapter)
                    checkProgressState(detail)

                    with(viewBinding) {
                        toolbarDetailTitle.text = title

                        Glide.with(requireContext())
                            .load(thumbnail)
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.image_loading)
                            .error(R.drawable.not_found_image)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(ivThumbnail)

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

                        shareGame(title, shortDescription, gameUrl)
                        addGameToFavorite(FavoriteUi.Base(id, thumbnail, title), title)
                        deleteGameFromFavorite(FavoriteUi.Base(id, thumbnail, title), title)
                    }
                }

                override fun map(message: String) = Unit
            })
        })
    }

    private fun checkProgressState(detail: DetailUi) {
        if (detail is DetailUi.Progress) {
            progress_detail.visibility = View.VISIBLE
        } else {
            progress_detail.visibility = View.GONE
        }
    }

    private fun shareGame(title: String, shortDescription: String, gameUrl: String) {
        viewBinding.fabShare.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                putExtra(
                    Intent.EXTRA_TEXT,
                    "${title}\n${shortDescription}\n$gameUrl\n" +
                            "\n${context?.getString(R.string.appGithub)}"
                )
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, title)
            startActivity(shareIntent)
        }
    }

    private fun addGameToFavorite(game: FavoriteUi.Base, title: String) {
        with(viewBinding) {
            fabAddFavorite.setOnClickListener {
                viewModel.saveToFavorite(game)
                Snackbar.make(it, "$title added to favorites", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteGameFromFavorite(game: FavoriteUi.Base, title: String) {
        with(viewBinding) {
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
        with(viewBinding) {
            if (!clicked) {
                fabAddFavorite.startAnimation(fromButton)
                fabDeleteFavorite.startAnimation(fromButton)
                fabShare.startAnimation(fromButton)
                fabAction.startAnimation(rotateOpen)
            } else {
                fabAddFavorite.startAnimation(toButton)
                fabDeleteFavorite.startAnimation(toButton)
                fabShare.startAnimation(toButton)
                fabAction.startAnimation(rotateClose)
            }
        }
    }

    private fun setVisibility(clicked: Boolean) {
        with(viewBinding) {
            if (!clicked) {
                fabAddFavorite.visibility = View.VISIBLE
                fabDeleteFavorite.visibility = View.VISIBLE
                fabShare.visibility = View.VISIBLE
            } else {
                fabAddFavorite.visibility = View.INVISIBLE
                fabDeleteFavorite.visibility = View.INVISIBLE
                fabShare.visibility = View.INVISIBLE
            }
        }
    }

    private fun setClickable(clicked: Boolean) {
        with(viewBinding) {
            if (!clicked) {
                fabAddFavorite.isClickable = true
                fabDeleteFavorite.isClickable = true
                fabShare.isClickable = true
            } else {
                fabAddFavorite.isClickable = false
                fabDeleteFavorite.isClickable = false
                fabShare.isClickable = false
            }
        }
    }
}