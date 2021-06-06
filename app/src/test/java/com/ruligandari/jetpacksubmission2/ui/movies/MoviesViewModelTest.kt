package com.ruligandari.jetpacksubmission2.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest{

    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<List<MoviesEntity>>

    @Before
    fun setUp(){
        viewModel = MoviesViewModel(dataRepository)

    }

    @Test
    fun testGetListPopularMovies(){
        val dummyMovies = DataDummy.generateDummyMovies()
        val movies = MutableLiveData<List<MoviesEntity>>()
        movies.value = dummyMovies

        `when`(dataRepository.getPopularMovies()).thenReturn(movies)

        val moviesEntities = viewModel.getListPopularMovies().value
        Mockito.verify(dataRepository).getPopularMovies()
        assertNotNull(moviesEntities)
        assertEquals(10, moviesEntities?.size)

        viewModel.getListPopularMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovies)
    }
}