package com.example.petsapplication.view.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapplication.databinding.ItemAppointmentBinding
import com.example.petsapplication.view.model.Appointment
import com.example.petsapplication.view.view.viewholder.AppointmentViewHolder


class AppointmentAdapter(private val listAppointment: MutableList<Appointment>, private val navController: NavController):RecyclerView.Adapter<AppointmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val binding = ItemAppointmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AppointmentViewHolder(binding, navController)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val appointment = listAppointment[position]
        holder.setItemAppointment(appointment)
    }

    override fun getItemCount(): Int {
        return listAppointment.size
    }
}