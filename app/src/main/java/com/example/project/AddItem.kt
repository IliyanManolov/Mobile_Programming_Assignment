package com.example.project

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.project.database.Meal
import com.example.project.database.MealViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AddItem : Fragment() {

    private lateinit var etFoodName : EditText
    private lateinit var etCaloriesCount : EditText
    private lateinit var mMealViewModel: MealViewModel

    private lateinit var bAddButton: Button

    private lateinit var pbAddItem: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_item, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddItem().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etFoodName = view.findViewById(R.id.etFoodName)
        etCaloriesCount = view.findViewById(R.id.etFoodCalories)
        pbAddItem = view.findViewById(R.id.addProgressBar)

        mMealViewModel = ViewModelProvider(this).get(MealViewModel::class.java)
        bAddButton = view.findViewById(R.id.addButton)

        val textWatcher = createTextWatcher()

        etFoodName.addTextChangedListener(textWatcher)
        etCaloriesCount.addTextChangedListener(textWatcher)

        bAddButton.setOnClickListener(){

            setupOnClickListener()
        }
    }

    private fun setupOnClickListener() {
        val foodName = etFoodName.text.toString()
        val caloriesString = etCaloriesCount.text.toString()

        if (foodName.isNullOrBlank()) {
            Toast.makeText(context, "Please enter a name for the food", Toast.LENGTH_LONG).show()
            return
        }

        if (caloriesString.isNullOrBlank()) {
            Toast.makeText(context, "Please enter a calories count", Toast.LENGTH_LONG).show()
            return
        }

        val caloriesInt = caloriesString.toIntOrNull()

        if (caloriesInt == null) {
            Toast.makeText(context, "Please enter a valid calories count", Toast.LENGTH_LONG).show()
            return
        }

        mMealViewModel.addMeal(
            Meal(null, foodName, caloriesInt)
        )

        Toast.makeText(context, "Meal added successfully", Toast.LENGTH_LONG).show()
        etFoodName.text.clear()
        etCaloriesCount.text.clear()
    }

    private fun createTextWatcher(): TextWatcher{
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            // Not exactly the best way of doing this
            override fun afterTextChanged(s: Editable) {

                val targetLength = 1 // string is NOT empty
                val foodNameLength = etFoodName.text.length
                val caloriesCountLenght = etCaloriesCount.text.length

                val progress = when {
                    foodNameLength >= targetLength && caloriesCountLenght >= targetLength -> 100 // Both filled
                    foodNameLength >= targetLength || caloriesCountLenght >= targetLength -> 50 // Only 1
                    else -> 0 // 0
                }

                pbAddItem.progress = progress
            }
        }
    }
}