package com.ruligandari.jetpacksubmission2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ruligandari.jetpacksubmission2.data.DataRepository
import com.ruligandari.jetpacksubmission2.di.Injection
import com.ruligandari.jetpacksubmission2.ui.detail.DetailMoviesViewModel
import com.ruligandari.jetpacksubmission2.ui.tvshows.TvShowsViewModel
import com.ruligandari.jetpacksubmission2.ui.detail.DetailTvShowViewModel
import com.ruligandari.jetpacksubmission2.ui.movies.MoviesViewModel

class ViewModelFactory private constructor(private val mRepository: DataRepository): ViewModelProvider.NewInstanceFactory(){
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideDatRepository())
            }
    }

    @Suppress("UNCHECKED_CAST")

    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        return when{
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(mRepository) as T
            }
            modelClass.isAssignableFrom(TvShowsViewModel::class.java) -> {
                TvShowsViewModel(mRepository) as T
            }
            modelClass.isAssignableFrom(DetailTvShowViewModel::class.java) ->{
                DetailTvShowViewModel(mRepository) as T
            }
            modelClass.isAssignableFrom(DetailMoviesViewModel::class.java) -> {
                DetailMoviesViewModel(mRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel Class: "+ modelClass.name)
        }
    }
}