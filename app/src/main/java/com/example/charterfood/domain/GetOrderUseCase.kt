package com.example.charterfood.domain

import com.example.charterfood.data.Order
import com.example.charterfood.data.OrderRepository
import javax.inject.Inject

class GetOrderUseCase @Inject constructor(private val repository: OrderRepository) {

    suspend fun getOrderFromApi(): MutableList<Order> {
        val orders = repository.getOrderFromApi()
        return when{
            orders.isNotEmpty()->{
                orders
            }
            else -> orders
        }
    }
}