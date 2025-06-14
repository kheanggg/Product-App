package com.example.productapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.productapp.databinding.FragmentCartBinding
import com.example.productapp.manager.CartManager

class CartFragment: Fragment() {

    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = CartManager.getCartItems()

        val displayText = if (items.isEmpty()) {
            "Your cart is empty."
        } else {
            items.joinToString(separator = "\n") { product ->
                "${product.name} - $${product.price}"
            }
        }
    }


}