package com.oxad.gogym.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oxad.gogym.R
import com.oxad.gogym.adapter.ExpandableAdapter
import com.oxad.gogym.model.ExpandableItem

class Trainer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainer)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val itemList = listOf(
            ExpandableItem("Rakesh Sharma", "Experience 12 years"),
            ExpandableItem("Ajay Mishra", "Experience 9 years", true),
            ExpandableItem("Gokulnath", "Experience 5 years")

        )

        recyclerView.adapter = ExpandableAdapter(itemList,this)
    }
}