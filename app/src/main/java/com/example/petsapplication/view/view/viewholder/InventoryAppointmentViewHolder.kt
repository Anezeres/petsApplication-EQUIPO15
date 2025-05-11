package com.example.petsapplication.view.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.petsapplication.databinding.ItemAppointmentBinding
import com.example.petsapplication.view.model.InventoryAppointment

class InventoryAppointmentViewHolder (binding: ItemAppointmentBinding):RecyclerView.ViewHolder(binding.root)
{
    val bindingItem = binding

    fun setItemAppointment(inventory: InventoryAppointment)
    {
        bindingItem.petName.text = inventory.pet_name
        bindingItem.pelSymptomsText.text = inventory.pel_symptoms
        bindingItem.indexAppointent.text = '1'.toString()
    }
}