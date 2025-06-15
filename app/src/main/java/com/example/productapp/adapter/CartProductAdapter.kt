package com.example.productapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.productapp.databinding.ViewholderCartProductBinding
import com.example.productapp.model.Product
import com.example.productapp.viewholder.CartProductViewHolder

class CartProductAdapter : ListAdapter<Product, CartProductViewHolder>(CartProductDiffCallback()) {

    var onItemClickListener: ((position: Int) -> Unit)? = null

    var onRemoveClickListener: ((position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewholderCartProductBinding.inflate(inflater, parent, false)
        return CartProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        val product = getItem(position)

        holder.bind(product) {
            onRemoveClickListener?.invoke(position)
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(position)
        }
    }
}

class CartProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}