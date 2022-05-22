package com.example.charterfood.data

import com.example.charterfood.network.OrderService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OrderRepository @Inject constructor(private val api: OrderService){

    suspend fun getOrderFromApi():MutableList<Order>{
        return api.getOrder()
    }

}