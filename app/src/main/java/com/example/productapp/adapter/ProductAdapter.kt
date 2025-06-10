package com.example.productapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.productapp.databinding.ViewholderProductBinding
import com.example.productapp.model.Product
import com.example.productapp.viewholder.ProductViewHolder


class ProductAdapter: ListAdapter<Product, ProductViewHolder>(ProductItemDiffCallback()) {

    var onItemClickListener: ((position: Int) -> Unit)? = null

    lateinit var allDataSet: List<Product>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewholderProductBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(position)
        }
    }

    fun filterByName(keyword: String) {
        val newList = allDataSet.filter { product -> product.name.contains(keyword, true)  }
        submitList(newList)
    }

}

class ProductItemDiffCallback: DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
       return oldItem.id == newItem.id
    }

}