package com.example.productapp.model

data class Product(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val price: Int,
    val specs: Specs,
)

// Nested data class for grouping the product detailed info
data class Specs(
    val os: String,
    val cpu: String,
    val memory: String,
    val screenSize: String,
)