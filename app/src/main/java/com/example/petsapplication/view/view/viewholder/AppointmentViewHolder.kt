package com.example.petsapplication.view.view.viewholder

import android.os.Bundle
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapplication.R
import com.example.petsapplication.databinding.ItemAppointmentBinding
import com.example.petsapplication.view.model.Appointment
import com.bumptech.glide.Glide


class AppointmentViewHolder (binding: ItemAppointmentBinding, navController: NavController): RecyclerView.ViewHolder(binding.root) {

    val bindingItem = binding
    val navController = navController
    fun setItemAppointment(appointment: Appointment) {

        val context = bindingItem.root.context
        bindingItem.tvIdAppointment.text = context.getString(R.string.id_appointment, appointment.id)
        bindingItem.tvPetName.text = appointment.petName
        bindingItem.tvSymptoms.text = appointment.symptoms
        Glide.with(context)
            .load(appointment.photoUri.toString())
            .into(bindingItem.imageViewPhotoUrl)

        bindingItem.cvAppointment.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("key", appointment)
            navController.navigate(R.id.action_homeAppointmentFragment_to_appointmentDetailsFragment, bundle)
        }

    }
}