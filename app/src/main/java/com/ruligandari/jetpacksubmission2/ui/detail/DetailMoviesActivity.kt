package com.ruligandari.jetpacksubmission2.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ruligandari.jetpacksubmission2.databinding.ActivityDetailBinding

class DetailMoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object{
       const val EXTRA_MOVIES = "extra_movies"
        const val BASE_URL = "https://image.tmdb.org/t/p/original"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}