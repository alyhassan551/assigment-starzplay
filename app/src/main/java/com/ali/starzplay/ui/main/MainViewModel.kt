package com.ali.starzplay.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.core.model.MediaItem
import com.ali.core.repository.MediaRepository
import com.ali.core.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val mediaRepository: MediaRepository) : ViewModel() {

    private val _mediaItems = MutableStateFlow<NetworkResult<List<MediaItem>>>(NetworkResult.Loading())
    val mediaItems: StateFlow<NetworkResult<List<MediaItem>>> get() = _mediaItems

    fun searchMedia(query: String) {
        viewModelScope.launch {
            mediaRepository.searchMedia(query).collect { result ->
                _mediaItems.value = result
            }
        }
    }
}
