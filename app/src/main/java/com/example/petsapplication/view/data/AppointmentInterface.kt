package com.example.petsapplication.view.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.petsapplication.view.model.InventoryAppointment
import androidx.lifecycle.LiveData


@Dao
interface AppointmentInterface {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(inventory: InventoryAppointment)

    @Query("SELECT * FROM InventoryAppointment")
    fun getList(): LiveData<MutableList<InventoryAppointment>> // 👈 CAMBIO AQUÍ

    @Delete
    suspend fun delete(inventory: InventoryAppointment)

    @Update
    suspend fun update(inventory: InventoryAppointment)
}
