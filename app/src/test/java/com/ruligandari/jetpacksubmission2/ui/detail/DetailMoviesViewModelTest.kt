package com.ruligandari.jetpacksubmission2.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.ruligandari.jetpacksubmission2.data.DataRepository
import com.ruligandari.jetpacksubmission2.data.source.local.MoviesEntity
import com.ruligandari.jetpacksubmission2.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMoviesViewModelTest {
    private val dummyMovies = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovies.id

    private lateinit var viewModel: DetailMoviesViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<MoviesEntity>

    @Before
    fun setUp() {
        viewModel = DetailMoviesViewModel(moviesRepository)
    }

    @Test
    fun getMoviesDetail() {
        val moviesDummy = MutableLiveData<MoviesEntity>()
        moviesDummy.value = dummyMovies

        `when`(moviesRepository.getMoviesDetail(movieId)).thenReturn(moviesDummy)
        val data = viewModel.getMoviesDetail(movieId).value as MoviesEntity

        assertNotNull(data)
        assertEquals(dummyMovies.id, data.id)
        assertEquals(dummyMovies.poster_path, data.poster_path)
        assertEquals(dummyMovies.title, data.title)
        assertEquals(dummyMovies.release_date, data.release_date)
        assertEquals(dummyMovies.runtime, data.runtime)
        assertEquals(dummyMovies.vote_average, data.vote_average)
        assertEquals(dummyMovies.overview, data.overview)

        viewModel.getMoviesDetail(movieId).observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}

