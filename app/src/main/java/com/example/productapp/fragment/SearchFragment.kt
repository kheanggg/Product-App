package com.example.productapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productapp.adapter.ProductAdapter
import com.example.productapp.databinding.FragmentSearchBinding
import com.example.productapp.model.Product
import com.example.productapp.service.ApiService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchFragment: Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private var adapter: ProductAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadProductList()

        initSearchView()
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
            } catch(ex: Exception) {
                Log.e("productapp", "Load product list error: ${ex.message}")
                showAlertDialog("Error", "Error while loading data from Server.")
            }
        }
    }

    private fun displayProductList(productList: List<Product>) {

        adapter = ProductAdapter()
        adapter?.allDataSet = productList
        adapter?.submitList(productList)
        adapter?.onItemClickListener = { position ->
            val product = productList[position]
            // Logic after tapped
            Log.d("productapp", product.name)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun showAlertDialog(title: String, message: String) {
        val alertBuilder = AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, a ->
                dialog.dismiss()
            }

        alertBuilder.show()
    }

    private fun initSearchView() {
        binding.edtSearch.doAfterTextChanged { edt ->
            adapter?.filterByName(edt.toString())
        }
    }

}