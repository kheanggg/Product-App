package com.example.productapp.manager

import com.example.productapp.model.Product

object CartManager {

    private val cartItems = mutableListOf<Product>()

    fun addToCart(product: Product) {
        cartItems.add(product)
    }

    fun removeFromCart(product: Product) {
        cartItems.remove(product)
    }

    fun getCartItems(): List<Product> = cartItems

    fun clearCart() {
        cartItems.clear()
    }
}