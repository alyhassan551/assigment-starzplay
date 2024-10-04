package com.ali.core.repository

import com.ali.core.mappers.toDomainModel
import com.ali.core.model.MediaItem
import com.ali.core.network.TMDbApiService
import com.ali.core.utils.NetworkResult
import com.ali.starzplay.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val apiService: TMDbApiService
) : MediaRepository {

    override suspend fun searchMedia(query: String): Flow<NetworkResult<List<MediaItem>>> = flow {
        emit(NetworkResult.Loading())

        try {
            val response = apiService.searchMedia(Constants.API_KEY, query)

            val mediaItems = response.results.map { it.toDomainModel() }

            emit(NetworkResult.Success(mediaItems))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "Unknown Error"))
        }
    }
}
