package com.example.productapp.viewholder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.productapp.databinding.ViewholderCartProductBinding
import com.example.productapp.model.Product

class CartProductViewHolder(private val binding: ViewholderCartProductBinding): ViewHolder((binding.root)) {

    fun bind(product: Product) {
        binding.txtName.text = product.name
    }
}