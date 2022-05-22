package com.example.charterfood.di

import com.example.charterfood.network.OrderApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/r-casarez-garcia-charter/57260b09dcbf415cef0f4fbe91ab468b/raw/7b27a6846259c6bd2b59d691f6e43c195e4e621d/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideOrderApiClient(retrofit: Retrofit): OrderApiClient{
        return retrofit.create(OrderApiClient::class.java)
    }
}