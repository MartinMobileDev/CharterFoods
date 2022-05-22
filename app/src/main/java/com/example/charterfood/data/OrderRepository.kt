package com.example.charterfood.data

import com.example.charterfood.network.OrderService
import javax.inject.Inject

class OrderRepository @Inject constructor(private val api: OrderService){

    suspend fun getOrderFromApi():MutableList<Order>{
        return api.getOrder()
    }

}