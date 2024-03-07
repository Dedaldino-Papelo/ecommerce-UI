package com.example.ecommerceui.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(
    @DrawableRes val imageRes: Int,
    @StringRes val categoryName: Int
)
