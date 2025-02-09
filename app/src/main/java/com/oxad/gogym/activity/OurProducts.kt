package com.oxad.gogym.activity

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.oxad.gogym.R
import com.oxad.gogym.adapter.ProductAdapter
import com.oxad.gogym.databinding.ActivityOurProductsBinding

import com.oxad.gogym.model.Product


class OurProducts : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding: ActivityOurProductsBinding
    private var productList: List<Product> = ArrayList()
    private  lateinit var adapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOurProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
    }
    private fun initUi(){

        binding.recyclerView.setLayoutManager(GridLayoutManager(this, 2)) // 2 columns
        productList = listOf(
            Product("Whey", 999.99, R.drawable.wheysample),
            Product("Creatine", 999.99, R.drawable.wheysample),
            Product("Whey Vanilla", 999.99, R.drawable.wheysample),
            Product("Fitness Manual", 999.99, R.drawable.wheysample),
            Product("Whey", 999.99, R.drawable.wheysample),
            Product("Creatine", 999.99, R.drawable.wheysample),
            Product("Whey Vanilla", 999.99, R.drawable.wheysample),
            Product("Fitness Manual", 999.99, R.drawable.wheysample)
        )

        adapter = ProductAdapter(this, productList)
        binding.recyclerView.setAdapter(adapter)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
        setclicklistener()
        // Set new gradient colors

    }
    private fun setclicklistener(){
        binding.backIv.setOnClickListener(this)


    }
    override fun onClick(p0: View?) {
        when (p0) {
            binding.backIv -> {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }
}