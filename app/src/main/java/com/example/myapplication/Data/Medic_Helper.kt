package com.example.myapplication.Data

import androidx.room.*

@Dao
interface Medic_Helper {
    @Query("SELECT * FROM medics")
    fun getAll(): List<Medic>

    @Query("SELECT * FROM medics WHERE id = :id")
    fun getById(id : Long) : Medic
    @Insert
    fun insert(weaters: Medic)

    @Update
    fun update(weaters: Medic)

    @Delete
    fun delete(weaters: Medic)
}