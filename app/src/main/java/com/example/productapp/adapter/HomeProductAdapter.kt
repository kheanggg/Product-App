package com.example.productapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.productapp.databinding.ViewholderHomeProductBinding
import com.example.productapp.model.Product
import com.example.productapp.viewholder.HomeProductViewHolder

class HomeProductAdapter : ListAdapter<Product, HomeProductViewHolder>(ProductItemDiffCallback()) {

    var onItemClickListener: ((position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewholderHomeProductBinding.inflate(layoutInflater, parent, false)
        return HomeProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeProductViewHolder, position: Int) {
        holder.bind(getItem(position))

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(position)
        }
    }
}
