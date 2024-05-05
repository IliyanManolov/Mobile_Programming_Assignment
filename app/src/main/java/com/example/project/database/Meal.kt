package com.example.project.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_table")
data class Meal (
    @PrimaryKey(autoGenerate = true)
    var id : Int?,
    @ColumnInfo(name = "food_name")
    val foodName : String,
    @ColumnInfo(name = "calories_count")
    val caloriesCount : Int
)