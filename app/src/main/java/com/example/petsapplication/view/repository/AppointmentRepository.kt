package com.example.petsapplication.view.repository

import android.content.Context
import com.example.petsapplication.view.data.AppointmentDB
import com.example.petsapplication.view.data.AppointmentInterface
import com.example.petsapplication.view.model.InventoryAppointment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.petsapplication.view.api.RetrofitClient
import retrofit2.HttpException
import androidx.lifecycle.LiveData

class AppointmentRepository(val context: Context){
    private var appointmentDB: AppointmentInterface = AppointmentDB.getDatabase(context).inventoryAppointment()
    suspend fun saveInventory(inventory:InventoryAppointment){
        withContext(Dispatchers.IO){
            appointmentDB.save(inventory)
        }
    }

    fun getListInventory(): LiveData<MutableList<InventoryAppointment>> {
        return appointmentDB.getList()
    }


    suspend fun deleteInventory(inventory: InventoryAppointment){
        withContext(Dispatchers.IO){
            appointmentDB.delete(inventory)
        }
    }

    suspend fun updateRepositoy(inventory: InventoryAppointment){
        withContext(Dispatchers.IO){
            appointmentDB.update(inventory)
        }
    }

    suspend fun getRandomDogImageUrl(breed: String): String? {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitClient.dogApiService.getRandomDogImageByBreed(breed).execute()
                if (response.isSuccessful) {
                    response.body()?.message
                } else null
            } catch (e: HttpException) {
                null
            } catch (e: Exception) {
                null
            }
        }
    }
}