package com.ruligandari.jetpacksubmission2.di

import com.ruligandari.jetpacksubmission2.data.DataRepository
import com.ruligandari.jetpacksubmission2.data.source.remote.RemoteDataSource

object Injection {
    fun provideDataRepository(): DataRepository{
        val remoteDataSource = RemoteDataSource.getInstance()
        return DataRepository.getInstance(remoteDataSource)
    }
}