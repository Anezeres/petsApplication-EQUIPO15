package com.example.petsapplication.view.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.petsapplication.view.model.InventoryAppointment
import com.example.petsapplication.view.repository.AppointmentRepository
import kotlinx.coroutines.launch

class AppointmentModel(application: Application) : AndroidViewModel(application)
{
    val context = getApplication<Application>()
    private val inventoryRepository = AppointmentRepository(context)


    val listInventory: LiveData<MutableList<InventoryAppointment>> = inventoryRepository.getListInventory()


    private val _progresState = MutableLiveData(false)
    val progresState: LiveData<Boolean> = _progresState

    fun save(inventory: InventoryAppointment) {
        viewModelScope.launch {
            _progresState.value = true
            try {
                inventoryRepository.saveInventory(inventory)
            } catch (e: Exception) {
                Log.e("AppointmentModel", "Error guardando cita", e)
            } finally {
                _progresState.value = false
            }
        }
    }


    fun  update(inventory: InventoryAppointment)
    {
        viewModelScope.launch {

            try {
                inventoryRepository.updateRepositoy(inventory)
                Log.d("UpdateRepositoy", "Inventory updated successfully: $inventory")
            } catch (e: Exception) {
                Log.d("UpdateRepositoy", "Error updating inventory: ${e.message}")
            }
        }
    }

    fun  delete(inventory: InventoryAppointment)
    {
        viewModelScope.launch {
            try {
                inventoryRepository.deleteInventory(inventory)
                Log.d("DeleteInventory", "Inventory deleted successfully: $inventory")
            } catch (e: Exception) {
                Log.d("DeleteInventory", "Error deleting inventory: ${e.message}")
            }
        }
    }


}