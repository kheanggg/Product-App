package com.example.productapp.manager

import com.example.productapp.model.Product

// Singleton
object CartManager {

    private val cartItems = mutableListOf<Product>()

    fun addToCart(product: Product) {
        cartItems.add(product)
    }

    fun removeFromCart(product: Product) {
        cartItems.remove(product)
    }

    fun getCartItems(): List<Product> = cartItems.toList()

    fun clearCart() {
        cartItems.clear()
    }
}