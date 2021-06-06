package com.ruligandari.jetpacksubmission2.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.ruligandari.jetpacksubmission2.utils.LiveDataTestUtil
import com.ruligandari.jetpacksubmission2.data.source.remote.RemoteDataSource
import com.ruligandari.jetpacksubmission2.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class DataRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val dataRepository = FakeDataRepository(remote)

    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val movieId = movieResponses[3].id
    private val tvShowsResponses = DataDummy.generateRemoteDummyTvshow()
    private val tvShowId = tvShowsResponses[3].id
    private val  movieResponse = DataDummy.generateRemoteDummyMovies()[2]
    private val tvShowsResponse  = DataDummy.generateRemoteDummyTvshow()[2]

    @Test
    fun testGetPopularMovies(){
        runBlocking {
            doAnswer { invacation ->
                (invacation.arguments[0] as RemoteDataSource.LoadPopularMoviesCallback).onAllMoviesReceived(movieResponses)
                null
            }. `when` (remote).getPopularMovies(any())
        }
//        buat livedata result
        val data = LiveDataTestUtil.getValue(dataRepository.getPopularMovies())
        runBlocking {
            verify(remote).getPopularMovies(any())
        }

        assertNotNull(data)
        assertEquals(movieResponses.size.toLong(), data.size.toLong())
    }

    @Test
    fun testGetPopularTvShows(){
        runBlocking {
            doAnswer { invacation ->
                (invacation.arguments[0] as RemoteDataSource.LoadPopularTvShowsCallback).onAllTvShowsReceived(tvShowsResponses)
                null
            }. `when` (remote).getPopularTvShows(any())
        }
//        buat livedata result
        val data = LiveDataTestUtil.getValue(dataRepository.getPopularTvShows())
        runBlocking {
            verify(remote).getPopularTvShows(any())
        }

        assertNotNull(data)
        assertEquals(tvShowsResponses.size.toLong(), data.size.toLong())
    }
    @Test
    fun testGetTvShowsDetail(){
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadTvShowsDetailCallback).onTvShowsDetailReceived(tvShowsResponse)
                null
            }. `when`(remote).getTvShowsDetail(eq(tvShowId), any())
        }
        val data = LiveDataTestUtil.getValue(dataRepository.getTvShowsDetail(tvShowId))

        runBlocking {
            verify(remote).getTvShowsDetail(eq(tvShowId), any())
        }
        assertNotNull(data)
        assertEquals(tvShowsResponse.id, data.id)

    }
    @Test
    fun testGetTvMoviesDetail(){
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadMoviesDetailCallback).onMoviesDetailReceived(movieResponse)
                null
            }. `when`(remote).getMoviesDetail(eq(movieId), any())
        }
        val data = LiveDataTestUtil.getValue(dataRepository.getMoviesDetail(movieId))

        runBlocking {
            verify(remote).getMoviesDetail(eq(movieId), any())
        }
        assertNotNull(data)
        assertEquals(movieResponse.id, data.id)

    }
}