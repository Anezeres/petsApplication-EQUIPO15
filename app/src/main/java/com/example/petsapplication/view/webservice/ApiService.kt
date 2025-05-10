package com.example.petsapplication.view.webservice

import com.example.petsapplication.view.utils.Constants.END_POINT_BREEDS
import com.example.petsapplication.view.utils.Constants.END_POINT_IMAGES
import com.example.petsapplication.view.model.BreedResponse
import com.example.petsapplication.view.model.Imagen
import retrofit2.http.GET

interface ApiService {

    @GET(END_POINT_IMAGES)
    suspend fun getRandomImage(): Imagen

    @GET(END_POINT_BREEDS)
    suspend fun getBreeds(): BreedResponse
}