package com.example.project.database

import androidx.lifecycle.LiveData

class MealRepository(private val mealDao : MealDao) {
    val getAll: LiveData<List<Meal>> = mealDao.getAll()

    fun addUser(meal: Meal) {mealDao.addMeal(meal)}

    fun deleteAllUsers(){
        mealDao.deleteAll()
    }
}