package com.dendi.android.gamessearchapp.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dendi.android.gamessearchapp.R
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.core.GamesApp
import com.dendi.android.gamessearchapp.databinding.FragmentDetailBinding

/**
 * @author Dendy-Jr on 04.11.2021
 * olehvynnytskyi@gmail.com
 */
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by lazy {
        (requireActivity().application as GamesApp).detailViewModel
    }

    private lateinit var screenshotAdapter: ScreenshotAdapter

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

        var id = 0

        val bundle: Bundle? = this.arguments
        if (arguments != null) {
            val detailId = bundle!!.getInt("id")
            id = detailId
        }
        viewModel.observeDetail(this, { detail ->
            detail.map(object : Abstract.AdapterDetailMapper {
                override fun map(
                    description: String,
                    developer: String,
                    freetogameProfileUrl: String,
                    gameUrl: String,
                    genre: String,
                    id: Int,
                    systemRequirements: SystemRequirementsUi,
                    platform: String,
                    publisher: String,
                    releaseDate: String,
                    screenshots: List<ScreenshotUi>,
                    shortDescription: String,
                    status: String,
                    thumbnail: String,
                    title: String,
                ) {
                    with(binding) {
                        toolbarDetail.title = title

                        Glide.with(this@DetailFragment)
                            .load(thumbnail)
                            .centerCrop()
                            .placeholder(R.drawable.image_loading)
                            .error(R.drawable.not_found_image)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(ivThumbnail)

                        screenshotAdapter.update(screenshots)

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
        viewModel.fetchDetail(id)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        screenshotAdapter = ScreenshotAdapter()
        with(binding) {
            recyclerViewDetail.adapter = screenshotAdapter
            recyclerViewDetail.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
