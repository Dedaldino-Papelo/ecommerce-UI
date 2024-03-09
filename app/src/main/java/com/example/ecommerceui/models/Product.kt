package com.example.ecommerceui.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Product(
    val productId: String,
    @DrawableRes val productImageRes: Int,
    @StringRes val productName: Int,
    val productPrice: Double
)
