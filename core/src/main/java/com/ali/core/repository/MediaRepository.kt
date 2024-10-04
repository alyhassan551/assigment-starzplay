package com.ali.core.repository

import com.ali.core.model.MediaItem
import com.ali.core.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MediaRepository {
    suspend fun searchMedia(query: String): Flow<NetworkResult<List<MediaItem>>>
}
