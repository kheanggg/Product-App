package com.example.productapp.viewholder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.productapp.databinding.ViewholderHomeProductBinding
import com.example.productapp.model.Product
import com.squareup.picasso.Picasso


class HomeProductViewHolder(private val binding: ViewholderHomeProductBinding) : ViewHolder((binding.root)) {

    fun bind(product: Product) {
        binding.txtProductName.text = product.name
        binding.txtProductPrice.setText('$' + product.price.toString())
        Picasso.get().load(product.imageUrl).into(binding.imgProduct)
    }
}