package com.ruligandari.jetpacksubmission2.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.ruligandari.jetpacksubmission2.R
import com.ruligandari.jetpacksubmission2.utils.DataDummy
import com.ruligandari.jetpacksubmission2.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummytvShows = DataDummy.generateDummyTvshow()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies(){
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }
    @Test
    fun loadMoviesDetail(){
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_description)).check(matches(isDisplayed()))
        pressBack()
    }

    @Test
    fun loadTvShows(){
        onView(withText(R.string.tvshows)).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummytvShows.size))
    }

    @Test
    fun loadTvShowsDetail(){
        onView(withText(R.string.tvshows)).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_description)).check(matches(isDisplayed()))
        pressBack()
    }

}