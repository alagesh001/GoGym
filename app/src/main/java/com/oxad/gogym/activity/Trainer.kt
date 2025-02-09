package com.oxad.gogym.activity

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oxad.gogym.R
import com.oxad.gogym.adapter.ExpandableAdapter
import com.oxad.gogym.databinding.ActivityGoalTrackerBinding
import com.oxad.gogym.databinding.ActivityTrainerBinding
import com.oxad.gogym.model.ExpandableItem
import com.oxad.gogym.utils.SharedPrefHelper
import com.oxad.gogym.utils.SpConstants

class Trainer : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding: ActivityTrainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
    }
    private fun initUi(){
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val itemList = listOf(
            ExpandableItem("Rakesh Sharma", "Experience 12 years"),
            ExpandableItem("Ajay Mishra", "Experience 9 years", true),
            ExpandableItem("Gokulnath", "Experience 5 years")

        )

        binding.recyclerView.adapter = ExpandableAdapter(itemList,this)
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