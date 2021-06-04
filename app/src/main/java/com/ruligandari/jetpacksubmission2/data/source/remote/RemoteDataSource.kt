package com.ruligandari.jetpacksubmission2.data.source.remote

import com.ruligandari.jetpacksubmission2.data.api.Client
import com.ruligandari.jetpacksubmission2.data.source.remote.response.MovieResponse
import com.ruligandari.jetpacksubmission2.data.source.remote.response.TvShowsResponse
import com.ruligandari.jetpacksubmission2.utils.EspressoIdlingResource
import retrofit2.await


class RemoteDataSource {

    companion object{
        @Volatile
        private var instance: RemoteDataSource?=null

        fun getInstance(): RemoteDataSource=
            instance?: synchronized(this){
                RemoteDataSource().apply{
                    instance = this
                }
            }
    }
    suspend fun getPopularMovies(callback: LoadPopularMoviesCallback){
        EspressoIdlingResource.increment()
        Client.apiInstance.getPopularMovies().await().result.let { listmovie ->
            callback.onAllMoviesReceived(listmovie)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getPopularTvShows(callback: LoadPopularTvShowsCallback){
        EspressoIdlingResource.increment()
        Client.apiInstance.getPopularTvShows().await().result.let { listTv ->
            callback.onAllTvShowsReceived(listTv)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getMoviesDetail(moviesId: Int, callback: LoadMoviesDetailCallback){
        EspressoIdlingResource.increment()
        Client.apiInstance.getMoviesDetail(moviesId).await().let {
                movie -> callback.onMoviesDetailReceived(movie)
            EspressoIdlingResource.decrement()
        }
    }
    suspend fun getTvShowsDetail(tvShowsId: Int, callback: LoadTvShowsDetailCallback){
        EspressoIdlingResource.increment()
        Client.apiInstance.getTvShowsDetail(tvShowsId).await().let {
                tvshow -> callback.onTvShowsDetailReceived(tvshow)
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadTvShowsDetailCallback {
        fun onTvShowsDetailReceived(tvshowsResponse: TvShowsResponse)
    }

    interface LoadMoviesDetailCallback {
        fun onMoviesDetailReceived(movieResponse: MovieResponse)

    }

    interface LoadPopularTvShowsCallback {
        fun onAllTvShowsReceived(tvResponse: List<TvShowsResponse>)
    }

    interface LoadPopularMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieResponse>)

    }
}


