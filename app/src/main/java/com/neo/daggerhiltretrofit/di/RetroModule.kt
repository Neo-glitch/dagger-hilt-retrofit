package com.neo.daggerhiltretrofit.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetroModule {
    private var url = "https://api.github.com/search/"

    @Singleton
    @Provides
    fun provideRetroInstance(): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit) =
        retrofit.create(RetrofitService::class.java)

}