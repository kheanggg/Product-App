package com.example.productapp.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.productapp.ProductDetailActivity
import com.example.productapp.adapter.HomeProductAdapter
import com.example.productapp.databinding.FragmentHomeBinding
import com.example.productapp.model.Product
import com.example.productapp.service.ApiService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var adapter: HomeProductAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadProductList()
    }

    private fun setupRecyclerView() {
        adapter = HomeProductAdapter()
        binding.recyclerViewHome.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerViewHome.adapter = adapter

        adapter?.onItemClickListener = { position ->
            adapter?.currentList?.getOrNull(position)?.let { product ->
                val intent = Intent(requireContext(), ProductDetailActivity::class.java)
                intent.putExtra("product", product)
                startActivity(intent)
            }
        }
    }

    private fun loadProductList() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://smlp-pub.s3.ap-southeast-1.amazonaws.com/mad-g9/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        lifecycleScope.launch {
            try {
                val productList = apiService.loadProductList()
                displayProductList(productList)
            } catch (ex: Exception) {
                Log.e("productapp", "Load product list error: ${ex.message}")
                showAlertDialog("Error", "Error while loading data from Server.")
            }
        }
    }

    private fun displayProductList(productList: List<Product>) {
        adapter?.submitList(productList)
    }

    private fun showAlertDialog(title: String, message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
