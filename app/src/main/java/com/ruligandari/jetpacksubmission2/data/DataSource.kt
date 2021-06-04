package com.ruligandari.jetpacksubmission2.data

import androidx.lifecycle.LiveData
import com.ruligandari.jetpacksubmission2.data.source.local.MoviesEntity
import com.ruligandari.jetpacksubmission2.data.source.local.TvShowsEntity

interface DataSource {
    fun getPopularMovies(): LiveData<List<MoviesEntity>>
    fun getPopularTvShows(): LiveData<List<TvShowsEntity>>

    fun getMoviesDetail(moviesId: Int): LiveData<MoviesEntity>
    fun getTvShowsDetail(tvId: Int): LiveData<TvShowsEntity>
}