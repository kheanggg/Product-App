package com.example.productapp.viewholder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.productapp.databinding.ViewholderSearchProductBinding
import com.example.productapp.model.Product

class SearchProductViewHolder(private val binding: ViewholderSearchProductBinding): ViewHolder((binding.root)) {

    fun bind(product: Product) {
        binding.txtName.text = product.name
    }
}