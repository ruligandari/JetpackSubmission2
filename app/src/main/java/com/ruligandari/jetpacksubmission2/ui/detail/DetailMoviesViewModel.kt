package com.ruligandari.jetpacksubmission2.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ruligandari.jetpacksubmission2.data.DataRepository
import com.ruligandari.jetpacksubmission2.data.source.local.MoviesEntity

class DetailMoviesViewModel(private var mRepository: DataRepository): ViewModel() {
    fun getMoviesDetail(moviesId: Int): LiveData<MoviesEntity> = mRepository.getMoviesDetail(moviesId)
}