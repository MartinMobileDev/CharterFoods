package com.example.charterfood.data

import com.google.gson.annotations.SerializedName


data class OrderModel(
    @SerializedName("order") var order: ArrayList<Order> = arrayListOf()
)