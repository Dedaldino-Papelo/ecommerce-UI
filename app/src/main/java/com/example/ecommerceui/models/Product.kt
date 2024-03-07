package com.example.ecommerceui.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Product(
    @DrawableRes val productImageRes: Int,
    @StringRes val productName: Int,
    val productPrice: Double
)
