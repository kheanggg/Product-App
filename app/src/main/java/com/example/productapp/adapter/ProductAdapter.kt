package com.example.productapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.productapp.databinding.ViewholderSearchProductBinding
import com.example.productapp.model.Product
import com.example.productapp.viewholder.SearchProductViewHolder


class ProductAdapter: ListAdapter<Product, SearchProductViewHolder>(ProductItemDiffCallback()) {

    var onItemClickListener: ((position: Int) -> Unit)? = null

    lateinit var allDataSet: List<Product>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewholderSearchProductBinding.inflate(layoutInflater, parent, false)
        return SearchProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchProductViewHolder, position: Int) {
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