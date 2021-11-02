package com.neo.daggerhiltretrofit.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.neo.daggerhiltretrofit.R
import com.neo.daggerhiltretrofit.databinding.RecyclerItemBinding
import com.neo.daggerhiltretrofit.model.RecyclerData
import com.neo.daggerhiltretrofit.model.RecyclerList
import java.util.*

class MainRvAdapter :RecyclerView.Adapter<MainRvAdapter.MyViewHolder>() {

    private var items: List<RecyclerData> = Collections.emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false))


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int =
        items.size


    @SuppressLint("NotifyDataSetChanged")
    fun setListData(newItems: List<RecyclerData>){
        this.items = newItems
        notifyDataSetChanged()
    }


    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val binding : RecyclerItemBinding = RecyclerItemBinding.bind(view)

        fun bind(data: RecyclerData){
            with(binding){
                tvTitle.text = data.name
                tvDesc.text =data.description
                Glide.with(thumbImage)
                    .load(data.owner.avatarUrl)
                    .apply(RequestOptions.centerCropTransform())
                    .into(thumbImage)
            }
        }

    }


}