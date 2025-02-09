package com.oxad.gogym.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.oxad.gogym.R
import com.oxad.gogym.bottomsheet.InputBottomSheet
import com.oxad.gogym.databinding.ActivityBasicinformationBinding
import com.oxad.gogym.databinding.ActivityGoalTrackerBinding
import com.oxad.gogym.databinding.FragmentHomeBinding
import com.oxad.gogym.utils.SharedPrefHelper
import com.oxad.gogym.utils.SpConstants

class GoalTracker : AppCompatActivity(), View.OnClickListener, InputBottomSheet.OnInputListener {
    private lateinit var binding: ActivityGoalTrackerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoalTrackerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
    }
        private fun initUi(){
            val weightdisp: String = getString(R.string.weight_display, SharedPrefHelper.getFloat(this,SpConstants.WEIGHT_DATA).toString())

            binding.weightTv.text = weightdisp
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
            binding.btnUpdate.setOnClickListener(this)


        }
        override fun onClick(p0: View?) {
            when(p0) {
                binding.backIv -> {
                    onBackPressedDispatcher.onBackPressed()
                }
                binding.btnUpdate -> showBottomSheet()

            }
        }
    val bottomSheet = InputBottomSheet()

    private fun showBottomSheet() {
        if(!bottomSheet.isAdded){
        bottomSheet.show(supportFragmentManager, "InputBottomSheet")
            }
    }

    override fun onInputReceived(value: Float) {
        SharedPrefHelper.save(this,SpConstants.WEIGHT_DATA,value)
        val weightdisp: String = getString(R.string.weight_display, value.toString())

        binding.weightTv.text =weightdisp

        Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show()
    }
}