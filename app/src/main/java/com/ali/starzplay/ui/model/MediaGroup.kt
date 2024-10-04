package com.ali.starzplay.ui.model

import com.ali.core.model.MediaItem

data class MediaGroup(
    val mediaType: String,
    val mediaItems: List<MediaItem>
)
