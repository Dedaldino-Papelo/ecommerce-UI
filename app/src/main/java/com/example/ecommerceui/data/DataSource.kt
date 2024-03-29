package com.example.ecommerceui.data

import com.example.ecommerceui.R
import com.example.ecommerceui.models.Category
import com.example.ecommerceui.models.Product
import com.example.ecommerceui.models.Recommended

object DataSource {
    val categories = listOf<Category>(
        Category(R.drawable.vector, R.string.category_name_1),
        Category(R.drawable.vector1, R.string.category_name_2),
        Category(R.drawable.vector2, R.string.category_name_3),
        Category(R.drawable.vector3, R.string.category_name_4),
        Category(R.drawable.vector1, R.string.category_name_2),
        Category(R.drawable.vector2, R.string.category_name_3),
        Category(R.drawable.vector3, R.string.category_name_4),
    )

    val products = listOf<Product>(
        Product("1", R.drawable.ellipse2, R.string.product_name_1, R.string.product_desc1, 7.2),
        Product("2", R.drawable.ellipse3, R.string.product_name_2, R.string.product_desc1, 6.2),
    )

    val recommended = listOf<Recommended>(
        Recommended(R.drawable.rectangle10),
        Recommended(R.drawable.rectangle9),
        Recommended(R.drawable.rectangle8)
    )
}