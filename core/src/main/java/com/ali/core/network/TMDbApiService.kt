package com.ali.core.network

import com.ali.core.model.MediaSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDbApiService {

    @GET("search/multi")
    suspend fun searchMedia(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): MediaSearchResponse
}
