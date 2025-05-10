package com.example.petsapplication.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.petsapplication.view.model.Appointment
import com.example.petsapplication.view.model.Imagen
import com.example.petsapplication.view.repository.AppointmentRepository
import kotlinx.coroutines.launch


class AppointmentViewModel(application: Application):AndroidViewModel(application) {

    val context = getApplication<Application>()
    private val appointmentRepository = AppointmentRepository(context)

    private val _listAppointment = MutableLiveData<MutableList<Appointment>>()
    val listAppointment: LiveData<MutableList<Appointment>> get() = _listAppointment

    private val _progressState = MutableLiveData(false)
    val progressState: LiveData<Boolean> = _progressState

    private val _imageURL = MutableLiveData<Imagen>()
    val imageURL: LiveData<Imagen> get() = _imageURL

    private val _breedList = MutableLiveData< Map<String, List<String>> >()
    val breedList: LiveData< Map<String, List<String>> > get() = _breedList

    fun saveAppointment(appointment: Appointment) {

        viewModelScope.launch {

            _progressState.value = true
            try {
                appointmentRepository.saveAppointment(appointment)
                _progressState.value = false
            } catch (e: Exception) {
                _progressState.value = false
            }
        }
    }

    fun updateAppointment(appointment: Appointment) {
        viewModelScope.launch {
            _progressState.value = true
            try {
                appointmentRepository.updateRepository(appointment)
                _progressState.value = false
            } catch (e: Exception) {
                _progressState.value = false
            }
        }
    }


    fun getListAppointment() {
        viewModelScope.launch {
            _progressState.value = true
            try {
                _listAppointment.value = appointmentRepository.getListAppointment()
                _progressState.value = false
            } catch (e: Exception) {
                _progressState.value = false
            }
        }
    }


    fun getImageURL() {
        viewModelScope.launch {
            _progressState.value = true
            try {
                _imageURL.value = appointmentRepository.getImageURL()
                _progressState.value = false
            } catch (e: Exception) {
                _progressState.value = false
            }
        }
    }

    fun getBreeds() {
        viewModelScope.launch {
            _progressState.value = true
            try {
                _breedList.value = appointmentRepository.getBreeds()
                _progressState.value = false
            } catch (e: Exception) {
                _progressState.value = false
            }
        }
    }

    fun deleteAppointment(appointment: Appointment) {
        viewModelScope.launch {
            _progressState.value = true
            try {
                appointmentRepository.deleteAppointment(appointment)
                _progressState.value = false
            } catch (e: Exception) {
                _progressState.value = false
            }
        }
    }

}