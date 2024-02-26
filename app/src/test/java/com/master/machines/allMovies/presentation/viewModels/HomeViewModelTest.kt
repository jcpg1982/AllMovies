package com.master.machines.allMovies.presentation.viewModels

import com.master.machines.allMovies.base.BaseViewModelTest
import com.master.machines.allMovies.base.TestUtils
import com.master.machines.allMovies.base.TestUtils.responseSuccess
import com.master.machines.allMovies.usesCase.MovieUsesCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.spyk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeViewModelTest : BaseViewModelTest() {

    @RelaxedMockK
    lateinit var movieUsesCase: MovieUsesCase
    lateinit var homeViewModel: HomeViewModel

    @Before
    @Throws(Exception::class)
    fun onBefore() {
        this.setUpBase()
        MockKAnnotations.init(this)
        homeViewModel = spyk(
            HomeViewModel(movieUsesCase)
        )
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `all movies when result is Success should handle correctly`() = runTest {
        val mockResult = TestUtils.responseResponseAllMoviesDTO.responseSuccess
        // Given
        coEvery { movieUsesCase.invoke(1, "") } returns flowOf(mockResult)
        // When
        homeViewModel.getAllMovies()
        // Then
        coVerify(exactly = 1) { homeViewModel.getAllMovies() }
        val result = homeViewModel.dataList.value
        assertEquals(mockResult.body()?.results, result)
    }
}