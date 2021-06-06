package com.ruligandari.jetpacksubmission2.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.ruligandari.jetpacksubmission2.data.DataRepository
import com.ruligandari.jetpacksubmission2.data.source.local.TvShowsEntity
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
class DetailTvShowViewModelTest{
    private val dummyTv = DataDummy.generateDummyTvshow()[0]
    private val tvShowsId =  dummyTv.id

    private lateinit var viewModel: DetailTvShowViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowsRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<TvShowsEntity>

    @Before
    fun setUp(){
        viewModel = DetailTvShowViewModel(tvShowsRepository)
    }

    @Test
    fun getTvShowsDetail(){
        val tvShowsDummy = MutableLiveData<TvShowsEntity>()
        tvShowsDummy.value = dummyTv

        `when`(tvShowsRepository.getTvShowsDetail(tvShowsId)).thenReturn(tvShowsDummy)
        val data = viewModel.getTvShowsDetail(tvShowsId).value as TvShowsEntity

        assertNotNull(data)
        assertEquals(dummyTv.id, data.id)
        assertEquals(dummyTv.poster_path, data.poster_path)
        assertEquals(dummyTv.name, data.name)
        assertEquals(dummyTv.first_air_date, data.first_air_date)
        assertEquals(dummyTv.episode_run_time, data.episode_run_time)
        assertEquals(dummyTv.vote_average, data.vote_average)
        assertEquals(dummyTv.overview, data.overview)

        viewModel.getTvShowsDetail(tvShowsId).observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}