package com.example.petsapplication.view.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.petsapplication.databinding.ItemAppointmentBinding
import com.example.petsapplication.view.model.InventoryAppointment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class InventoryAppointmentViewHolder (binding: ItemAppointmentBinding):RecyclerView.ViewHolder(binding.root)
{
    val bindingItem = binding

    fun setItemAppointment(inventory: InventoryAppointment)
    {
        bindingItem.petName.text = inventory.pet_name
        bindingItem.pelSymptomsText.text = inventory.pel_symptoms
        bindingItem.indexAppointent.text = "#${inventory.id}"



        // Cargar imagen con Glide
        Glide.with(bindingItem.imgPet.context)
            .load(inventory.imageUrl)
            .transform(CircleCrop())
            .into(bindingItem.imgPet)
    }
}