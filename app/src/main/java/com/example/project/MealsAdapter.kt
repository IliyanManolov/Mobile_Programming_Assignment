package com.example.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.database.Meal

class MealsAdapter(
    var meals : List<Meal>
) : RecyclerView.Adapter<MealsAdapter.MealsViewHolder>() {

    inner class MealsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val tvFoodName : TextView = itemView.findViewById(R.id.tvFoodName)
        val tvCalories : TextView = itemView.findViewById(R.id.tvCalories)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.meal_tracker_row, parent, false)
        return MealsViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        holder.tvFoodName.text = meals[position].foodName
        holder.tvCalories.text = meals[position].caloriesCount.toString()
    }

    override fun getItemCount(): Int {
        return meals.count()
    }

    fun setData(meals: List<Meal>){
        this.meals = meals
        notifyDataSetChanged()
    }
}