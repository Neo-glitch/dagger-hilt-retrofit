package com.neo.daggerhiltretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.neo.daggerhiltretrofit.adapter.MainRvAdapter
import com.neo.daggerhiltretrofit.databinding.ActivityMainBinding
import com.neo.daggerhiltretrofit.model.RecyclerList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val mViewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }
    private lateinit var mAdapter: MainRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initRv()
    }

    private fun initRv() {
        mBinding.recylerView.layoutManager = LinearLayoutManager(this)

        mAdapter = MainRvAdapter()
        mBinding.recylerView.adapter = mAdapter

        getData()
    }

    private fun getData() {
        mViewModel.getRvListObserver().observe(this,
            {
                if (it != null) {
                    mAdapter.setListData(it.items)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Unable to get data, check your internet",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        mViewModel.makeApiCall()
    }
}