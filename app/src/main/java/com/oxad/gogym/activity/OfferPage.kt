package com.oxad.gogym.activity

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.oxad.gogym.R
import com.oxad.gogym.adapter.CashbackAdapter
import com.oxad.gogym.adapter.ExpandableAdapter
import com.oxad.gogym.databinding.ActivityOfferPageBinding
import com.oxad.gogym.databinding.ActivityTrainerBinding
import com.oxad.gogym.model.CashbackItem
import com.oxad.gogym.model.ExpandableItem

class OfferPage : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding: ActivityOfferPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOfferPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()

    }
    private fun initUi(){

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

    val cashbackList = listOf(
        CashbackItem("Annual Membership", "50%", 17, R.color.yellow),
        CashbackItem("Couple Offer", "40%", 12, R.color.pink),
        CashbackItem("Winter Offer", "20%", 21, R.color.blue),
        CashbackItem("Referral offer", "20%", 19, R.color.purple)
    )
    binding.recyclerView.adapter = CashbackAdapter(cashbackList)
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
        when(p0) {
            binding.backIv -> {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }
}