package com.ali.starzplay.ui.player

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ali.starzplay.databinding.FragmentPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerFragment : Fragment() {

    private var _binding: FragmentPlayerBinding? = null
    private val binding get() = _binding!!

    private var exoPlayer: ExoPlayer? = null
    private lateinit var mediaUrl: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        mediaUrl = arguments?.getString("mediaUrl")
            ?: "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4" // Default URL or handle null case

        initializePlayer()
    }

    private fun initializePlayer() {
        exoPlayer = ExoPlayer.Builder(requireContext()).build().apply {
            binding.playerView.player = this

            val mediaItem = MediaItem.fromUri(Uri.parse(mediaUrl))
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        releasePlayer()
        _binding = null
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }

    private fun releasePlayer() {
        exoPlayer?.release()
        exoPlayer = null
    }
}
