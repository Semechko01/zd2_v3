package com.example.myapplication.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Medic::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {

    abstract fun medic_Helper(): Medic_Helper

    companion object {
        @Volatile private var obj: DataBase? = null

        fun getInstance(context: Context): DataBase =
            obj ?: synchronized(this) {
                obj ?: Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "my_medic.db"
                ).build().also { obj = it }
            }
    }
}