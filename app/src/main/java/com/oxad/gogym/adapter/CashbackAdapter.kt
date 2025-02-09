package com.oxad.gogym.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.oxad.gogym.R
import com.oxad.gogym.model.CashbackItem

class CashbackAdapter(private val cashbackList: List<CashbackItem>) :
    RecyclerView.Adapter<CashbackAdapter.CashbackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CashbackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cashback, parent, false)
        return CashbackViewHolder(view)
    }

    override fun onBindViewHolder(holder: CashbackViewHolder, position: Int) {
        val item = cashbackList[position]
        holder.shopName.text = item.shopName
        holder.cashbackText.text = "${item.couponCount} days remaining"
        holder.discountvalue.text = "${item.cashbackPercentage}"
        holder.cardView.setCardBackgroundColor(holder.itemView.context.getColor(item.colorRes))
    }

    override fun getItemCount(): Int = cashbackList.size

    class CashbackViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val shopName: TextView = view.findViewById(R.id.shopName)
        val cashbackText: TextView = view.findViewById(R.id.cashbackText)
        val discountvalue: TextView = view.findViewById(R.id.discount_tv)
        val cardView: CardView = view.findViewById(R.id.cardView)
    }
}
