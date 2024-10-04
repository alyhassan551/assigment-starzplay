package com.ali.starzplay.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import com.ali.core.repository.MediaRepository
import com.ali.starzplay.ui.main.MainViewModel

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideMainViewModel(
        mediaRepository: MediaRepository
    ): MainViewModel {
        return MainViewModel(mediaRepository)
    }
}
