package com.ruligandari.jetpacksubmission2.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ruligandari.jetpacksubmission2.R
import com.ruligandari.jetpacksubmission2.databinding.ActivityMainBinding
import com.ruligandari.jetpacksubmission2.ui.adapter.SectionPagerAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        activityMainBinding.viewpager.adapter = sectionPagerAdapter
        activityMainBinding.tabs.setupWithViewPager(activityMainBinding.viewpager)

        supportActionBar?.elevation = 0f
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.rgb(0,77,170)))
    }
}