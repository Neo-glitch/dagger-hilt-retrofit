package com.neo.daggerhiltretrofit.model

import com.google.gson.annotations.SerializedName

data class RecyclerList(@SerializedName("items")val items: MutableList<RecyclerData>)