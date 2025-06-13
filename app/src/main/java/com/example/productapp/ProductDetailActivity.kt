package com.example.productapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.productapp.databinding.ActivityProductDetailBinding
import com.example.productapp.model.Product
import com.squareup.picasso.Picasso

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product = intent.getSerializableExtra("product") as Product

        Picasso.get().load(product.imageUrl).into(binding.imgProduct)
        binding.txtProductName.setText(product.name.toString())
        binding.txtProductPrice.setText('$' + product.price.toString())
        binding.txtProductOS.setText(product.specs.os.toString())
        binding.txtProductCPU.setText(product.specs.cpu.toString())
        binding.txtProductMemory.setText(product.specs.memory.toString())
        binding.txtProductSize.setText(product.specs.screenSize.toString())

        binding.btnBack.setOnClickListener {
            goBack()
        }
    }

    private fun goBack() {
        finish()
    }


}