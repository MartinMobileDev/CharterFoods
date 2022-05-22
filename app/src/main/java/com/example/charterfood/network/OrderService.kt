package com.example.charterfood.network

import com.example.charterfood.data.Order
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OrderService @Inject constructor(private val api: OrderApiClient) {

    suspend fun getOrder(): MutableList<Order> {
        return withContext(Dispatchers.IO) {
            val response = api.getOrder()
            (response.body()?.order ?: emptyList()) as MutableList<Order>
        }
    }
}