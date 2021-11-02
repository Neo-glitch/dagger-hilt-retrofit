package com.neo.daggerhiltretrofit.di

import com.neo.daggerhiltretrofit.model.RecyclerList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("repositories")
    fun loadDataFromApi(@Query("q")query:String):Call<RecyclerList>
}