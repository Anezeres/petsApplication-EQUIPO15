package com.example.petsapplication.view.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapplication.databinding.ItemAppointmentBinding
import com.example.petsapplication.view.model.InventoryAppointment
import com.example.petsapplication.view.view.viewholder.InventoryAppointmentViewHolder

class ListAppointmentAdapter(
    private val listAppointment: MutableList<InventoryAppointment>,
    private val onItemClick: (InventoryAppointment) -> Unit
) : RecyclerView.Adapter<InventoryAppointmentViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InventoryAppointmentViewHolder {
        val binding = ItemAppointmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InventoryAppointmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InventoryAppointmentViewHolder, position: Int) {
        val inventory = listAppointment[position]
        holder.setItemAppointment(inventory)
        holder.itemView.setOnClickListener {
            onItemClick(inventory)
        }
    }

    override fun getItemCount(): Int {
        return listAppointment.size
    }
}