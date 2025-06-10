package com.example.productapp.service

import com.example.productapp.model.Product
import retrofit2.http.GET

interface ApiService {

    @GET("product-list.json")
    suspend fun loadProductList(): List<Product>

}