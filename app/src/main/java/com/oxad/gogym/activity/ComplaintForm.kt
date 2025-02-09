package com.oxad.gogym.activity

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.oxad.gogym.R
import com.oxad.gogym.adapter.ProductAdapter
import com.oxad.gogym.databinding.ActivityComplaintFormBinding
import com.oxad.gogym.databinding.ActivityOurProductsBinding
import com.oxad.gogym.model.Product

class ComplaintForm : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding: ActivityComplaintFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComplaintFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()

        val complaintTypes = arrayOf("Equipment Issue", "Hygiene Problem", "Staff Behavior", "Other")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, complaintTypes)
        binding.spinnerComplaintType.adapter = adapter

    }
    private fun initUi(){


        setclicklistener()
        // Set new gradient colors

    }
    private fun setclicklistener(){
        binding.backIv.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)

    }
    override fun onClick(p0: View?) {
        when (p0) {
            binding.backIv -> {
                onBackPressedDispatcher.onBackPressed()
            }

            binding.btnSubmit -> onSubmit()
        }
    }
    private fun onSubmit()
    {

        val complaintType = binding.spinnerComplaintType.selectedItem.toString()
        val complaintDesc = binding.etComplaintDescription.text.toString().trim()

        if ( complaintDesc.isEmpty()) {
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, "Complaint submitted successfully!", Toast.LENGTH_LONG).show()


        binding.etComplaintDescription.text.clear()
        binding.spinnerComplaintType.setSelection(0)
    }
}