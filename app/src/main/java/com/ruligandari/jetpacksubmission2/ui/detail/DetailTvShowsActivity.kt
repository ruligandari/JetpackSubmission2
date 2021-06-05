package com.ruligandari.jetpacksubmission2.ui.detail

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ruligandari.jetpacksubmission2.R
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
        val id = intent.getIntExtra(EXTRA_TV_SHOW, 0)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.tvItemDescription.justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
        }
        showLoading(true)

        viewModel.getTvShowsDetail(id).observe(this, {
            val runtime: String = it.episode_run_time?.get(0).toString()

            if (it != null){
                binding.apply {
                    tvItemTitle.text = it.name
                    tvItemRelease.text = it.first_air_date
                    tvItemRating.text = it.vote_average
                    tvItemDuration.text = "${runtime}minutes"
                    tvItemDescription.text = it.overview

                    Glide.with(this@DetailTvShowsActivity)
                        .load(BASE_URL + it.poster_path)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)).error(R.drawable.ic_error)
                        .into(imgPoster)
                    showLoading(false)
                }
            }
        })
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}