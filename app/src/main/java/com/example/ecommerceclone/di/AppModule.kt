package com.example.ecommerceclone.di

import com.example.ecommerceclone.data.remote.ApiService
import com.example.ecommerceclone.data.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideApiService():ApiService{
        return RetrofitClient.apiService
    }
}