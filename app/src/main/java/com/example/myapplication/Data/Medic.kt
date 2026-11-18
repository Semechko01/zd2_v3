package com.example.myapplication.Data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "medics")
data class Medic (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val time: String,
    val temp: Double,
    val dav: Double
)