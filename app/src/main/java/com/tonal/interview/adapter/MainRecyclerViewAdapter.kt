package com.tonal.interview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tonal.interview.R
import com.tonal.interview.data.Movement

class MainRecyclerViewAdapter:
    ListAdapter<Movement, MainRecyclerViewAdapter.MovementViewHolder>(MovementDiff()) {

    inner class MovementViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun bind(item: Movement?) {
            val name = view.findViewById<TextView>(R.id.name)
            val thumbnail = view.findViewById<ImageView>(R.id.thumbnail)
            name.text = item?.name ?: "No Name"
            Glide.with(view).load(item!!.thumbnailMediaUrl).centerCrop().into(thumbnail);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovementViewHolder {
        return MovementViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movement,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovementViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class MovementDiff: DiffUtil.ItemCallback<Movement>() {
    override fun areItemsTheSame(oldItem: Movement, newItem: Movement): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movement, newItem: Movement): Boolean {
        return oldItem.name == newItem.name
    }

}