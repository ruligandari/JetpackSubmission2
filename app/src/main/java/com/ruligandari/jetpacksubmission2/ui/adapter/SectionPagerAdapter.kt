package com.ruligandari.jetpacksubmission2.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ruligandari.jetpacksubmission2.R
import com.ruligandari.jetpacksubmission2.ui.movies.MoviesFragment
import com.ruligandari.jetpacksubmission2.ui.tvshows.TvShowsFragement

class SectionPagerAdapter(private val mContext: Context, fragmentManager: FragmentManager):
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    companion object {
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tvshows)
    }
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = MoviesFragment()
            1 -> fragment = TvShowsFragement()
        }
        return fragment!!
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }
}