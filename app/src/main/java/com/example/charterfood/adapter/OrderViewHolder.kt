package com.example.charterfood.adapter

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.charterfood.CharterFoodApp
import com.example.charterfood.R
import com.example.charterfood.data.Order
import com.example.charterfood.databinding.ItemOrderBinding
import com.example.charterfood.utils.Constants.USD_VALUE
import java.math.RoundingMode

class OrderViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemOrderBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun render(order: Order, onClickListener: (Order) -> Unit) {
        binding.apply {
            tvType.visibility = if (order.type.isNullOrEmpty()) View.GONE else View.VISIBLE
            tvType.text = order.type
            isVisible(order.visibility)
            tvShape.visibility = if (order.shape.isNullOrEmpty()) View.GONE else View.VISIBLE
            tvShape.text = order.shape
            tvGrain.visibility = if (order.grain.isNullOrEmpty()) View.GONE else View.VISIBLE
            tvGrain.text = order.grain
            tvSize.visibility = if (order.size.isNullOrEmpty()) View.GONE else View.VISIBLE
            tvSize.text = order.size
            tvMeat.visibility = if (order.meat.isNullOrEmpty()) View.GONE else View.VISIBLE
            tvMeat.text = order.meat
            tvLength.visibility = if (order.length == null) View.GONE else View.VISIBLE
            tvLength.text = order.length.toString()
            tvName.visibility = if (order.name.isNullOrEmpty()) View.GONE else View.VISIBLE
            tvName.text = order.name
            tvToppings.visibility = if (order.toppings.isNullOrEmpty()) View.GONE else View.VISIBLE
            tvToppings.text = TextUtils.join(", ", order.toppings)
            tvSauce.visibility = if (order.sauce.isNullOrEmpty()) View.GONE else View.VISIBLE
            tvSauce.text = TextUtils.join(", ", order.sauce)
            tvIngredients.visibility =
                if (order.ingredients.isNullOrEmpty()) View.GONE else View.VISIBLE
            tvIngredients.text = TextUtils.join(", ", order.ingredients)
            cvOrder.setOnClickListener { onClickListener(order) }
            order.type?.let { setImage(it) }
            calculateUsdAndEuro(order)
            tvTotalIndividualEur.text = "${order.priceEuro.toString()} €"
            tvTotalIndividualUsd.text = "US $${order.priceUsd.toString()}"
            individualPayBtn.setOnClickListener {
                onClickListener(order)
            }
        }
    }

    private fun calculateUsdAndEuro(order: Order) {
        when (order.type) {
            "salad" -> if (order.ingredients.size > 2) {
                val sum = (2 + (order.ingredients.size - 2) * 0.5)
                val sumUsd = sum * USD_VALUE
                order.priceEuro = sum.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
                order.priceUsd = sumUsd.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
            } else {
                val sumUsd = 2 * USD_VALUE
                order.priceEuro = 2.toBigDecimal()
                order.priceUsd = sumUsd.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
            }
            "pizza" -> when (order.size) {
                "small" -> if (order.sauce.size > 0) {
                    val sum = (5 + 5 + (order.toppings.size))
                    val sumUsd = sum * USD_VALUE
                    order.priceEuro = sum.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
                    order.priceUsd = sumUsd.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
                } else {
                    val sum = (5 + (order.toppings.size))
                    val sumUsd = sum * USD_VALUE
                    order.priceEuro = sum.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
                    order.priceUsd = sumUsd.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
                }
                "medium" -> if (order.sauce.size > 0) {
                    val sum = (10 + 5 + (order.toppings.size))
                    val sumUsd = sum * USD_VALUE
                    order.priceEuro = sum.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
                    order.priceUsd = sumUsd.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
                } else {
                    val sum = (10 + (order.toppings.size))
                    val sumUsd = sum * USD_VALUE
                    order.priceEuro = sum.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
                    order.priceUsd = sumUsd.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
                }
                "big" -> if (order.sauce.size > 0) {
                    val sum = (15 + 5 + (order.toppings.size))
                    val sumUsd = sum * USD_VALUE
                    order.priceEuro = sum.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
                    order.priceUsd = sumUsd.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
                } else {
                    val sum = (15 + (order.toppings.size))
                    val sumUsd = sum * USD_VALUE
                    order.priceEuro = sum.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
                    order.priceUsd = sumUsd.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
                }
                else -> {
                    println("Neither of those")
                }
            }
            "bread" -> {
                order.priceEuro = 3.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
                order.priceUsd = (3 * USD_VALUE).toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
            }

            "sausage" -> when (order.length) {
                5 -> {
                    order.priceEuro = 1.50.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
                    order.priceUsd =
                        (1.50 * USD_VALUE).toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
                }
                10 -> {
                    order.priceEuro = 3.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
                    order.priceUsd =
                        (3 * USD_VALUE).toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
                }
                else -> {
                    println("Neither of those")
                }
            }
            else -> {
                println("Neither of those")
            }
        }
    }

    private fun isVisible(visibility: Boolean) {
        binding.expandedDescription.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    private fun setImage(type: String) {
        when (type) {
            "salad" -> Glide.with(view.context).load(R.drawable.salad_logo).centerCrop()
                .into(binding.ivOrder)
            "pizza" -> Glide.with(view.context).load(R.drawable.pizza_logo).centerCrop()
                .into(binding.ivOrder)
            "bread" -> Glide.with(view.context).load(R.drawable.bread_logo).centerCrop()
                .into(binding.ivOrder)
            "sausage" -> Glide.with(view.context).load(R.drawable.sausage_logo).centerCrop()
                .into(binding.ivOrder)
        }
    }

    fun setListener(order: Order, listener: OnClickListener) {
        with(binding.individualPayBtn) {
            setOnClickListener { listener.onDelete(order) }
        }
    }
}