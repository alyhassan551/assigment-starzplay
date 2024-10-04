package com.ali.starzplay.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ali.core.model.MediaItem
import com.ali.starzplay.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var mediaItem: MediaItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mediaItem = arguments?.getSerializable("mediaItem") as? MediaItem
            ?: throw IllegalStateException("MediaItem not found in arguments")

        binding.txtDetailTitle.text = mediaItem.title
        binding.txtDetailOverview.text = mediaItem.overview

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/${mediaItem.posterPath}")
            .into(binding.imgDetailBackdrop)

        if (mediaItem.mediaType == "movie" || mediaItem.mediaType == "tv") {
            binding.btnPlay.visibility = View.VISIBLE
            binding.btnPlay.setOnClickListener {

                val action = DetailFragmentDirections.actionDetailFragmentToPlayerFragment()
                findNavController().navigate(action)
            }
        } else {
            binding.btnPlay.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



