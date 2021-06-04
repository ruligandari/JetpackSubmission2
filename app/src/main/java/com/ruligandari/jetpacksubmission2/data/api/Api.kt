package com.ruligandari.jetpacksubmission2.data.api

import com.ruligandari.jetpacksubmission2.BuildConfig
import com.ruligandari.jetpacksubmission2.data.source.remote.response.ContentResponse
import com.ruligandari.jetpacksubmission2.data.source.remote.response.MovieResponse
import com.ruligandari.jetpacksubmission2.data.source.remote.response.TvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): Call<ContentResponse<MovieResponse>>
    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): Call<ContentResponse<TvShowsResponse>>
    @GET("movie/{movie_id}")
    fun getMoviesDetail(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): Call<MovieResponse>
    @GET("tv/{tv_id}")
    fun getTvShowsDetail(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): Call<TvShowsResponse>
}