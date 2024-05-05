package com.example.project.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MealDao {

    @Insert
    fun addMeal(meal: Meal)

    // Order in descending order to get the newest first
    @Query("SELECT * FROM meal_table ORDER BY id DESC")
    fun getAll(): LiveData<List<Meal>>

    @Query("DELETE FROM meal_table")
    fun deleteAll()
}