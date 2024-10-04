package com.ali.starzplay.ui.main
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ali.core.utils.NetworkResult
import com.ali.starzplay.R
import com.ali.starzplay.adapter.MediaCarouselAdapter
import com.ali.starzplay.databinding.FragmentMainBinding
import com.ali.starzplay.ui.model.MediaGroup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    private lateinit var carouselAdapter: MediaCarouselAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carouselAdapter = MediaCarouselAdapter { mediaItem ->
            val bundle = Bundle().apply {
                putSerializable("mediaItem", mediaItem)
            }
            findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }

        binding.recyclerCarousels.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = carouselAdapter
        }

        viewModel.searchMedia("fun")

        observeMediaItems()

        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchMedia(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun observeMediaItems() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.mediaItems.collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {

                    }
                    is NetworkResult.Success -> {

                        val mediaItems = result.data ?: emptyList()
                        val mediaGroups = mediaItems.groupBy { it.mediaType }.map { (type, items) ->
                            MediaGroup(mediaType = type, mediaItems = items)
                        }
                        carouselAdapter.submitList(mediaGroups)
                    }
                    is NetworkResult.Error -> {
                        Toast.makeText(context, result.message ?: "An error occurred", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


