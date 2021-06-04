package com.ruligandari.jetpacksubmission2.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ruligandari.jetpacksubmission2.viewmodel.ViewModelFactory
import com.ruligandari.jetpacksubmission2.databinding.ActivityDetailBinding

class DetailTvShowsActivity: AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding

    companion object{
        const val EXTRA_TV_SHOW = "extra_tv_show"
        const val BASE_URL = "https://image.tmdb.org/t/p/original"
    }

    @SuppressLint("WrongConstant", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this, ViewModelFactory.getInstance()).get(DetailTvShowViewModel::class.java)
    }
}