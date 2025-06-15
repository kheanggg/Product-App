package com.example.productapp.viewholder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.productapp.databinding.ViewholderCartProductBinding
import com.example.productapp.model.Product

class CartProductViewHolder(private val binding: ViewholderCartProductBinding): ViewHolder((binding.root)) {

    fun bind(product: Product, onRemove: (() -> Unit)? = null) {
        binding.txtProductName.text = product.name
        binding.txtProductPrice.text = '$' + product.price.toString()

        binding.btnRemove.setOnClickListener {
            onRemove?.invoke()
        }
    }
}