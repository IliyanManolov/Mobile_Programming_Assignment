package com.example.project

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SearchItem : Fragment() {


    private lateinit var etSearchedFood : EditText
    private lateinit var btnSearch : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search__item, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchItem().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etSearchedFood = view.findViewById(R.id.etSearchItem)
        btnSearch = view.findViewById(R.id.btnSearch)

        btnSearch.setOnClickListener(){

            var desiredFoodString = etSearchedFood.text.toString()

            if (desiredFoodString.isNullOrBlank()){
                Toast.makeText(context, "Please enter a food to search calories for", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            
            gotoUrl(desiredFoodString)
        }
    }

    private fun gotoUrl(desiredFood: String) {
        var desiredFoodUrl = desiredFood.split(" ").joinToString("+")

        val baseUrlString : String = "https://www.nutracheck.co.uk/CaloriesIn/Product/Search?desc="

        var uri : Uri = Uri.parse(baseUrlString + desiredFoodUrl)

        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}