package com.example.productapp.viewholder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.productapp.databinding.ViewholderProductBinding
import com.example.productapp.model.Product

class ProductViewHolder(private val binding: ViewholderProductBinding): ViewHolder((binding.root)) {

    fun bind(product: Product) {
        binding.txtName.text = product.name
    }
}