package com.oxad.gogym.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oxad.gogym.R
import com.oxad.gogym.model.Product


class ProductAdapter(private val context: Context, productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private val productList: List<Product> = productList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product: Product = productList[position]
        holder.productTitle.setText(product.title)
        holder.productPrice.text = "₹" + product.price
        holder.productImage.setImageResource(product.imageResId)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productImage: ImageView = itemView.findViewById(R.id.productImage)
        var productTitle: TextView = itemView.findViewById(R.id.productTitle)
        var productPrice: TextView = itemView.findViewById(R.id.productPrice)
    }
}
