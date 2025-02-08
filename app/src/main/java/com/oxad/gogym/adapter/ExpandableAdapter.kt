package com.oxad.gogym.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.oxad.gogym.R
import com.oxad.gogym.model.ExpandableItem

class ExpandableAdapter(private val itemList: List<ExpandableItem>,private val context: Context) :
    RecyclerView.Adapter<ExpandableAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDetails: TextView = itemView.findViewById(R.id.tvDetails)
        val expandableLayout: View = itemView.findViewById(R.id.expandableLayout)
        val ivProfile: ImageView = itemView.findViewById(R.id.imgProfile)
        val greentick_show: ImageView = itemView.findViewById(R.id.greentick_iv)
        val card_item: View = itemView.findViewById(R.id.item_viewcard)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.trainer_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]

        holder.tvTitle.text = item.title
        holder.tvDetails.text = item.details


        Glide.with(context)
            .load("https://greenyellow-octopus-821386.hostingersite.com/images/gympicsample.jpg")
            .placeholder(R.drawable.ic_launcher_foreground) // Default image
            .transition(DrawableTransitionOptions.withCrossFade(400))
            .circleCrop() // Make image round
            .into(holder.ivProfile)


        // Set visibility based on expansion state
        holder.greentick_show.visibility = if (item.myCoach) View.VISIBLE else View.GONE
        holder.expandableLayout.visibility = if (item.isExpanded) View.VISIBLE else View.GONE

        // Handle click to expand/collapse
        holder.card_item.setOnClickListener {
            item.isExpanded = !item.isExpanded
            notifyItemChanged(position) // Refresh item view
        }
    }

    override fun getItemCount(): Int = itemList.size
}
