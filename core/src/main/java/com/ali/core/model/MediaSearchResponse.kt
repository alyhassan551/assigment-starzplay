package com.ali.core.model

data class MediaSearchResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<MediaDto>
)

data class MediaDto(
    val id: Int,
    val media_type: String,
    val title: String?,
    val name: String?,
    val overview: String?,
    val poster_path: String?,
    val backdrop_path: String?
)
