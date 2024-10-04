package com.ali.core.mappers

import com.ali.core.model.MediaDto
import com.ali.core.model.MediaItem

fun MediaDto.toDomainModel(): MediaItem {
    return MediaItem(
        id = this.id,
        mediaType = this.media_type,
        title = this.title ?: this.name ?: "",
        overview = this.overview,
        posterPath = this.poster_path,
        backdropPath = this.backdrop_path
    )
}
