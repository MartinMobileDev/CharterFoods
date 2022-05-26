package com.example.charterfood.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charterfood.data.Order
import com.example.charterfood.domain.GetOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(private val getOrderUseCase: GetOrderUseCase) :
    ViewModel() {

    val orderListModel = MutableLiveData<MutableList<Order>>()
    val isLoading = MutableLiveData<Boolean>()
    val checkoutSum = MutableLiveData<BigDecimal>()

    fun onCreateApi() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getOrderUseCase.getOrderFromApi()
            if (!result.isNullOrEmpty()) {
                orderListModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

    fun sumCheckoutValues() {
        var sum = 0f
        if (!orderListModel.value.isNullOrEmpty()) {
            orderListModel.value?.forEach {
                if(it.priceEuro!=null)
                sum += it.priceEuro?.toFloat()!!
            }
            checkoutSum.postValue(sum.toBigDecimal())
        } else {
            checkoutSum.postValue(0.toBigDecimal())
        }
    }

    fun individualPay(order: Order) {
        orderListModel.value?.remove(order)
        sumCheckoutValues()
    }

    fun payAll() {
        orderListModel.value?.clear()
        sumCheckoutValues()
    }
}