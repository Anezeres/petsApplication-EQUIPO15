package com.example.petsapplication.view.model
import com.google.gson.annotations.SerializedName

data class Imagen (
    @SerializedName("message") val url: String,
    @SerializedName("status") val status: String
)