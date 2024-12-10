package com.example.apilearning.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apilearning.R
import com.example.apilearning.data.model.DrinkDetailModel
import com.example.apilearning.databinding.RecyclerViewRowBinding

class RecyclerDrinkLayoutAdapter(
    private val drinkDetailModel: DrinkDetailModel
) : RecyclerView.Adapter<RecyclerDrinkLayoutAdapter.DrinkLayoutViewHolder>() {


    class DrinkLayoutViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //handle individual UI elements and their settings
        //the binding name comes from the name of the recycler row xml file
        // (in this case, "recycler_view_row)
        val binding = RecyclerViewRowBinding.bind(view)

        fun setupUI(drinkName: String, drinkCategory: String, drinkImage: String) {
            binding.fullName.text = drinkName
            binding.abbrevName.text=drinkCategory
            Glide.with(binding.root.context)
                .load(drinkImage)
                .placeholder(R.drawable.ic_launcher_background) //what to display while image is loading
                //.error() what to display when the image is not successfully retrieved
                .into(binding.recycleViewImage)
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
        holder.setupUI(drinkDetailModel.drinks?.get(position)?.strDrink.toString(),
            drinkDetailModel.drinks?.get(position)?.strCategory.toString(),
            drinkDetailModel.drinks?.get(position)?.strDrinkThumb.toString())
    }

}