package com.example.charterfood.data

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal


data class Order(
    @SerializedName("type") var type: String? = null,
    @SerializedName("shape") var shape: String? = null,
    @SerializedName("grain") var grain: String? = null,
    @SerializedName("size") var size: String? = null,
    @SerializedName("meat") var meat: String? = null,
    @SerializedName("length") var length: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("toppings") var toppings: ArrayList<String> = arrayListOf(),
    @SerializedName("sauce") var sauce: ArrayList<String> = arrayListOf(),
    @SerializedName("ingredients") var ingredients: ArrayList<String> = arrayListOf(),
    var priceEuro: BigDecimal? = null,
    var priceUsd: BigDecimal? = null,
    var visibility: Boolean = false

)