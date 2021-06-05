package com.ruligandari.jetpacksubmission2.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ruligandari.jetpacksubmission2.data.source.local.MoviesEntity
import com.ruligandari.jetpacksubmission2.data.source.local.TvShowsEntity
import com.ruligandari.jetpacksubmission2.data.source.remote.RemoteDataSource
import com.ruligandari.jetpacksubmission2.data.source.remote.response.MovieResponse
import com.ruligandari.jetpacksubmission2.data.source.remote.response.TvShowsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class DataRepository private constructor(private val remoteDataSource: RemoteDataSource): DataSource {

    companion object {
        @Volatile
        private var instance : DataRepository? = null

        fun getInstance(remoteData: RemoteDataSource): DataRepository =
            instance ?: synchronized(this){
                DataRepository(remoteData).apply { instance = this }
            }
    }

    override fun getPopularMovies(): LiveData<List<MoviesEntity>> {
        val listMovies = MutableLiveData<List<MoviesEntity>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getPopularMovies(object: RemoteDataSource.LoadPopularMoviesCallback{
                override fun onAllMoviesReceived(movieResponse: List<MovieResponse>) {
                    val moviesList = ArrayList<MoviesEntity>()
                    for (response in movieResponse){
                        val movie = MoviesEntity(
                            response.id,
                            response.poster_path,
                            response.title,
                            response.release_date,
                            response.runtime,
                            response.vote_average,
                            response.overview
                        )
                        moviesList.add(movie)
                    }
                    listMovies.postValue(moviesList)
                }
            })
        }
        return listMovies
    }

    override fun getPopularTvShows(): LiveData<List<TvShowsEntity>> {
        val listTvShows = MutableLiveData<List<TvShowsEntity>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getPopularTvShows(object: RemoteDataSource.LoadPopularTvShowsCallback{
                override fun onAllTvShowsReceived(tvResponse: List<TvShowsResponse>) {
                    val tvList = ArrayList<TvShowsEntity>()
                    for (response in tvResponse){
                        val tvshow = TvShowsEntity(
                            response.id,
                            response.poster_path,
                            response.name,
                            response.first_air_date,
                            response.episode_run_time,
                            response.vote_average,
                            response.overview
                        )
                        tvList.add(tvshow)
                    }
                    listTvShows.postValue(tvList)
                }
            })
        }

        return listTvShows
    }

    override fun getMoviesDetail(moviesId: Int): LiveData<MoviesEntity> {
        val movieResult = MutableLiveData<MoviesEntity>()
        CoroutineScope(IO).launch {
            remoteDataSource.getMoviesDetail(moviesId, object : RemoteDataSource.LoadMoviesDetailCallback{
                override fun onMoviesDetailReceived(movieResponse: MovieResponse) {
                    val movie = MoviesEntity(
                        movieResponse.id,
                        movieResponse.poster_path,
                        movieResponse.title,
                        movieResponse.release_date,
                        movieResponse.runtime,
                        movieResponse.vote_average,
                        movieResponse.overview
                    )
                    movieResult.postValue(movie)
                }
            })
        }
        return movieResult
    }

    override fun getTvShowsDetail(tvId: Int): LiveData<TvShowsEntity> {
        val tvResult = MutableLiveData<TvShowsEntity>()
        CoroutineScope(IO).launch {
            remoteDataSource.getTvShowsDetail(tvId, object : RemoteDataSource.LoadTvShowsDetailCallback{
                override fun onTvShowsDetailReceived(tvshowsResponse: TvShowsResponse) {
                    val tvshow = TvShowsEntity(
                        tvshowsResponse.id,
                        tvshowsResponse.poster_path,
                        tvshowsResponse.name,
                        tvshowsResponse.first_air_date,
                        tvshowsResponse.episode_run_time,
                        tvshowsResponse.vote_average,
                        tvshowsResponse.overview
                    )
                    tvResult.postValue(tvshow)
                }
            })
        }
        return tvResult
    }
}