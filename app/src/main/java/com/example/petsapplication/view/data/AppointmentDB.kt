package com.example.petsapplication.view.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.petsapplication.view.model.InventoryAppointment

@Database(entities = [InventoryAppointment::class], version = 2)
abstract class AppointmentDB  : RoomDatabase() {
    abstract fun inventoryAppointment(): AppointmentInterface

    companion object {
        @Volatile private var INSTANCE: AppointmentDB? = null

        fun getDatabase(context: Context): AppointmentDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppointmentDB::class.java,
                    "dog_app.db"
                )
                    .fallbackToDestructiveMigration()        // ← aquí
                    .build()
                    .also { INSTANCE = it }
            }
    }
}
