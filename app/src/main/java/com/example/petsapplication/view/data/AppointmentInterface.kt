package com.example.petsapplication.view.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.petsapplication.view.model.InventoryAppointment

@Dao
interface AppointmentInterface
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(inventory: InventoryAppointment)

    @Query("SELECT * FROM InventoryAppointment")
    suspend fun getList(): MutableList<InventoryAppointment>

    @Delete
    suspend fun delete(inventory: InventoryAppointment)

    @Update
    suspend fun update(inventory: InventoryAppointment)
}