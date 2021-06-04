package com.ruligandari.jetpacksubmission2.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ruligandari.jetpacksubmission2.data.DataRepository
import com.ruligandari.jetpacksubmission2.data.source.local.MoviesEntity

class MoviesViewModel(private val mRepository: DataRepository): ViewModel() {
    fun getListPopularMovies(): LiveData<List<MoviesEntity>> = mRepository.getPopularMovies()
}