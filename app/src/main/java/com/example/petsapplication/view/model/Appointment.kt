package com.example.petsapplication.view.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.net.URL

@Entity
data class Appointment(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val petName: String,
    val petBreed: String,
    val ownerName: String,
    val ownerPhone: String,
    val symptoms: String,
    val photoUrl: String
) : Serializable {
    val photoUri: URL
        get() = URL(photoUrl)
}