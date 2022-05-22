package com.example.charterfood.network

import com.example.charterfood.data.OrderModel
import retrofit2.Response
import retrofit2.http.GET

interface OrderApiClient {
    @GET("order.json")
    suspend fun getOrder(): Response<OrderModel>
}