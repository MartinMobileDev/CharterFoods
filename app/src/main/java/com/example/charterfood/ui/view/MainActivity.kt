package com.example.charterfood.ui.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.charterfood.adapter.OnClickListener
import com.example.charterfood.adapter.OrderAdapter
import com.example.charterfood.data.Order
import com.example.charterfood.databinding.ActivityMainBinding
import com.example.charterfood.ui.viewModel.OrderViewModel
import com.example.charterfood.utils.Constants.USD_VALUE
import com.example.charterfood.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import java.math.RoundingMode

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val orderViewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        orderViewModel.onCreateApi()
        initRecyclerView()
        setUpViewModel()
        payAll()
    }

    @SuppressLint("SetTextI18n")
    private fun setUpViewModel() {
        orderViewModel.isLoading.observe(this) {
            binding.progress.isVisible = it
        }
        orderViewModel.checkoutSum.observe(this) {
            binding.tvTotalEur.text = "${it.setScale(2, RoundingMode.HALF_EVEN)} €"
            val usdValue = it * USD_VALUE.toBigDecimal()
            binding.tvTotalUsd.text = "US $${usdValue.setScale(2, RoundingMode.HALF_EVEN)}"
        }
    }

    private fun initRecyclerView() {
        binding.ordersRv.layoutManager = LinearLayoutManager(this)
        orderViewModel.orderListModel.observe(this) { checkOutList ->
            binding.ordersRv.adapter = OrderAdapter(this, checkOutList) { order ->
                onItemSelected(order)
            }
        }
            binding.ordersRv.viewTreeObserver.addOnGlobalLayoutListener {
            orderViewModel.sumCheckoutValues()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onItemSelected(order: Order) {
        order.visibility = !order.visibility
        binding.ordersRv.adapter?.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onDelete(order: Order) {
        orderViewModel.individualPay(order)
        binding.ordersRv.adapter?.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun payAll() {
        binding.btnPayAll.setOnClickListener {
            orderViewModel.payAll()
            toast("Thank you for your purchase!!!")
            binding.ordersRv.adapter?.notifyDataSetChanged()
        }
    }
}