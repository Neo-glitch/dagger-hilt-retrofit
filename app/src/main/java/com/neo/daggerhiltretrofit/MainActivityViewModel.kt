package com.neo.daggerhiltretrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.neo.daggerhiltretrofit.di.RetrofitService
import com.neo.daggerhiltretrofit.model.RecyclerList
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor( var service: RetrofitService) : ViewModel(){

    private var listForRv: MutableLiveData<RecyclerList>

    init {
        listForRv = MutableLiveData()
    }

    fun getRvListObserver(): MutableLiveData<RecyclerList>{
        return listForRv
        makeApiCall()
    }


    fun makeApiCall(){
        val call =service.loadDataFromApi("newyork")
        call.enqueue(object: Callback<RecyclerList>{
            override fun onResponse(call: Call<RecyclerList>?, response: Response<RecyclerList>?) {
                val value: RecyclerList? = if (response!!.isSuccessful) {
                    response.body()
                } else{
                        null
                }
                listForRv.postValue(value)
            }

            override fun onFailure(call: Call<RecyclerList>?, t: Throwable?) {
                listForRv.postValue(null)
            }
        })
    }

}