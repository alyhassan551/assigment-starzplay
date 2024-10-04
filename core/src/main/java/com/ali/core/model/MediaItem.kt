package com.ali.core.model

import java.io.Serializable

data class MediaItem(
    val id: Int,
    val mediaType: String,
    val title: String,
    val overview: String?,
    val posterPath: String?,
    val backdropPath: String?
) : Serializable

