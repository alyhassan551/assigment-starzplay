package com.ali.starzplay.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ali.core.model.MediaItem
import com.ali.core.repository.MediaRepository
import com.ali.core.utils.NetworkResult
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mediaRepository: MediaRepository

    @Before
    fun setUp() {
        // Set the main dispatcher to the test dispatcher
        Dispatchers.setMain(testDispatcher)

        // Initialize mock repository and ViewModel
        mediaRepository = mock(MediaRepository::class.java)
        mainViewModel = MainViewModel(mediaRepository)
    }

    @After
    fun tearDown() {
        // Reset the dispatcher after tests
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @Test
    fun `when searchMedia is called, repository is triggered and data is updated with success`() = runTest {
        // Arrange: Prepare fake data and mock repository response
        val fakeMediaItems = listOf(MediaItem(1, "movie", "Title", "Overview", "posterPath", "backdropPath"))
        `when`(mediaRepository.searchMedia("query")).thenReturn(flow {
            emit(NetworkResult.Loading())
            emit(NetworkResult.Success(fakeMediaItems))
        })

        // Act: Trigger the ViewModel function
        mainViewModel.searchMedia("query")

        // Allow all coroutines to run
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert: Verify repository was called and ViewModel updated state
        verify(mediaRepository).searchMedia("query")
        assertTrue(mainViewModel.mediaItems.value is NetworkResult.Success)
        assertEquals(fakeMediaItems, (mainViewModel.mediaItems.value as NetworkResult.Success).data)
    }

    @Test
    fun `when searchMedia is called, repository returns error`() = runTest {
        // Arrange: Prepare an error message and mock repository response
        val errorMessage = "Network Error"
        `when`(mediaRepository.searchMedia("query")).thenReturn(flow {
            emit(NetworkResult.Loading())
            emit(NetworkResult.Error<List<MediaItem>>(errorMessage))
        })

        // Act: Trigger the ViewModel function
        mainViewModel.searchMedia("query")

        // Allow all coroutines to run
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert: Verify repository was called and ViewModel updated state
        verify(mediaRepository).searchMedia("query")
        assertTrue(mainViewModel.mediaItems.value is NetworkResult.Error)
        assertEquals(errorMessage, (mainViewModel.mediaItems.value as NetworkResult.Error).message)
    }

    @Test
    fun `when searchMedia is called, ViewModel initially emits loading state`() = runTest {
        // Arrange: Mock the repository to return a flow
        `when`(mediaRepository.searchMedia("query")).thenReturn(flow {
            emit(NetworkResult.Loading())
            // Add some delay here if needed to simulate loading
        })

        // Act: Trigger the ViewModel function
        mainViewModel.searchMedia("query")

        // Allow all coroutines to run
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert: Verify the initial state is loading
        assertTrue(mainViewModel.mediaItems.value is NetworkResult.Loading)
    }
}


