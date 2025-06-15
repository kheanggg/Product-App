package com.example.productapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productapp.ProductDetailActivity
import com.example.productapp.adapter.CartProductAdapter
import com.example.productapp.adapter.SearchProductAdapter
import com.example.productapp.databinding.FragmentCartBinding
import com.example.productapp.manager.CartManager
import com.example.productapp.model.Product

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    private var adapter: CartProductAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cartItems = CartManager.getCartItems()
        displayCartList(cartItems)
    }

    private fun displayCartList(cartList: List<Product>) {
        adapter = CartProductAdapter()
        adapter?.submitList(cartList)

        adapter?.onItemClickListener = { position ->
            val product = cartList[position]
            val intent = Intent(requireContext(), ProductDetailActivity::class.java)
            intent.putExtra("product", product)
            startActivity(intent)
        }

        adapter?.onRemoveClickListener = { position ->
            val productToRemove = cartList[position]
            CartManager.removeFromCart(productToRemove)

            val updatedList = CartManager.getCartItems()
            adapter?.submitList(updatedList)
            toggleEmptyView(updatedList)
        }

        binding.recyclerViewCart.adapter = adapter
        binding.recyclerViewCart.layoutManager = LinearLayoutManager(context)

        toggleEmptyView(cartList)
    }

    private fun toggleEmptyView(cartList: List<Product>) {
        if (cartList.isEmpty()) {
            binding.recyclerViewCart.visibility = View.GONE
            binding.textViewEmptyCart.visibility = View.VISIBLE
        } else {
            binding.recyclerViewCart.visibility = View.VISIBLE
            binding.textViewEmptyCart.visibility = View.GONE
        }
    }
}