package com.ruligandari.jetpacksubmission2.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ruligandari.jetpacksubmission2.data.DataRepository
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
import com.ruligandari.jetpacksubmission2.data.source.local.TvShowsEntity as TvShowsEntity

@RunWith(MockitoJUnitRunner::class)
class TvShowsViewModelTest{
    private lateinit var viewModel: TvShowsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<List<TvShowsEntity>>

    @Before
    fun setUp(){
        viewModel= TvShowsViewModel(dataRepository)
    }

    @Test
    fun testGetListPopularTvShows(){
        val dummyTvShows = DataDummy.generateDummyTvshow()
        val tvshows = MutableLiveData<List<TvShowsEntity>>()
        tvshows.value = dummyTvShows

        `when`(dataRepository.getPopularTvShows()).thenReturn(tvshows)
        val tvShowsEntities = viewModel.getListPopularTvShows().value
        Mockito.verify(dataRepository).getPopularTvShows()
        assertNotNull(tvShowsEntities)
        assertEquals(10, tvShowsEntities?.size)

        viewModel.getListPopularTvShows().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShows)
    }

}