package com.example.apilearning.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apilearning.MainViewModel
import com.example.apilearning.R
import com.example.apilearning.data.model.DrinkDetailModel
import com.example.apilearning.databinding.RecyclerViewRowBinding

class RecyclerDrinkLayoutAdapter(
    private val drinkDetailModel: DrinkDetailModel
) : RecyclerView.Adapter<RecyclerDrinkLayoutAdapter.DrinkLayoutViewHolder>() {


    var viewModelInstance = MainViewModel()
    var drinksList = DrinkDetailModel()

    class DrinkLayoutViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //handle individual UI elements and their settings
        //the binding name comes from the name of the recycler row xml file
        // (in this case, "recycler_view_row)
        val binding = RecyclerViewRowBinding.bind(view)

        fun setupUI(drinkName: String) {
            binding.abbrevName.text = drinkName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkLayoutViewHolder {
        //attach the UI/XML file to be used on each row
        return DrinkLayoutViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return drinkDetailModel.drinks?.size!!
    }

    override fun onBindViewHolder(holder: DrinkLayoutViewHolder, position: Int) {
        //handle the current item in the list
        //position refers to what place the item is in the recycler

        //use an adapter here to do the API call?
        holder.setupUI(drinksList.drinks?.get(position)?.strDrink.toString())
        //holder.setupUI(drinkDetailModel.drinks.get(position))
    }

}