package com.ruligandari.jetpacksubmission2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ruligandari.jetpacksubmission2.R
import com.ruligandari.jetpacksubmission2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }
}