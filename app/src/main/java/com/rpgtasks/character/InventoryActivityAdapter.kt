package com.rpgtasks.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rpgtasks.databinding.ItemInventoryBinding

class InventoryActivityAdapter(private val data : List<Inventory>) : RecyclerView.Adapter<InventoryActivityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryActivityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemInventoryBinding.inflate(inflater, parent, false)
        return InventoryActivityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InventoryActivityViewHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.size
}