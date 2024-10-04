package com.ali.core.di

import com.ali.core.network.TMDbApiService
import com.ali.core.repository.MediaRepository
import com.ali.core.repository.MediaRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMediaRepository(
        apiService: TMDbApiService
    ): MediaRepository {
        return MediaRepositoryImpl(apiService)
    }
}
