package com.ruligandari.jetpacksubmission2.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.ruligandari.jetpacksubmission2.data.DataRepository
import com.ruligandari.jetpacksubmission2.data.source.local.TvShowsEntity

class DetailTvShowViewModel(private val mRepository: DataRepository): ViewModel(){
    fun getTvShowsDetail(tvShowId: Int): LiveData<TvShowsEntity> = mRepository.getTvShowsDetail(tvShowId)
}