package com.example.petsapplication.view.repository

import android.content.Context
import com.example.petsapplication.view.data.AppointmentDB
import com.example.petsapplication.view.data.AppointmentDao
import com.example.petsapplication.view.model.Appointment
import com.example.petsapplication.view.model.Imagen
import com.example.petsapplication.view.webservice.ApiService
import com.example.petsapplication.view.webservice.ApiUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppointmentRepository(val context: Context) {

    private var appointmentDao: AppointmentDao = AppointmentDB.getDatabase(context).appointmentDao()
    private var apiServiceImage: ApiService = ApiUtils.getApiService()


    suspend fun saveAppointment(appointment: Appointment) {
        withContext(Dispatchers.IO) {
            // segundo plano corre saveAppointment
            appointmentDao.saveAppointment(appointment)
        }
    }

    suspend fun updateRepository(appointment: Appointment){
        withContext(Dispatchers.IO){
            appointmentDao.updateAppointment(appointment)
        }
    }

    suspend fun getListAppointment():MutableList<Appointment>{
        return withContext(Dispatchers.IO){
            appointmentDao.getListAppointment()
        }
    }

    suspend fun deleteAppointment(appointment: Appointment){
        withContext(Dispatchers.IO){
            appointmentDao.deleteAppointment(appointment)
        }
    }

    suspend fun updateAppointment(appointment: Appointment){
        withContext(Dispatchers.IO){
            appointmentDao.updateAppointment(appointment)
        }
    }

    suspend fun getImageURL(): Imagen {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiServiceImage.getRandomImage()
                response
            } catch (e: Exception) {
                e.printStackTrace()
                Imagen(url = "", status = "")
            }
        }
    }

    suspend fun getBreeds(): Map<String, List<String>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiServiceImage.getBreeds()
                response.breeds
            } catch (e: Exception) {
                e.printStackTrace()
                emptyMap<String, List<String>>()
            }
        }
    }
}