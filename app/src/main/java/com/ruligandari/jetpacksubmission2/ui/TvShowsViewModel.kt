package com.ruligandari.jetpacksubmission2.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ruligandari.jetpacksubmission2.data.DataRepository
import com.ruligandari.jetpacksubmission2.data.source.local.TvShowsEntity

class TvShowsViewModel(private val mRepository: DataRepository): ViewModel() {
    fun getListPopularTvShows(): LiveData<List<TvShowsEntity>> = mRepository.getPopularTvShows()
}