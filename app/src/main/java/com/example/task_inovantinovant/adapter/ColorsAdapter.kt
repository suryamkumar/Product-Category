package com.example.task_inovantinovant.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task_inovantinovant.databinding.ItemColorCardBinding
import com.squareup.picasso.Picasso

class ColorsAdapter(private val imageList: List<String>) :
    RecyclerView.Adapter<ColorsAdapter.ColorViewHolder>() {

    inner class ColorViewHolder(val binding: ItemColorCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val binding = ItemColorCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val imageUrl = imageList[position]
        Picasso.get()
            .load(imageUrl)
            .into(holder.binding.colorImage)
    }

    override fun getItemCount(): Int = imageList.size
}

