package com.example.apilearning

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apilearning.adapters.RecyclerDrinkLayoutAdapter
import com.example.apilearning.data.ResponseState
import com.example.apilearning.data.model.DrinkDetailModel
import com.example.apilearning.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.Response


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModelInstance: MainViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModelInstance = ViewModelProvider(this).get(MainViewModel::class.java)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModelInstance.cocktails.observe(this) { value ->
            //THE RESULT MUST BE SENT TO A SEPARATE FUNCTION TO ENSURE THE RESULT FROM THE
            //ASYNC FUNCTION CALL IS ACTUALLY RECEIVED BEFORE SUPPLYING IT TO THE RECYCLE VIEW
            when(value){
                is ResponseState.Loading -> updateDrinksListLoading()
                is ResponseState.Success -> updateDrinksListSuccess(value.result)
                is ResponseState.Fail -> updateDrinksListFail(value.failureString)
            }

            //updateDrinksList(value)
        }
        mainViewModelInstance.getDrinksList()



    }


    private fun updateDrinksListLoading(){
        binding.apply {
            dataImageView.setImageResource(R.drawable.ic_gear)
            statusTextView.text=("Loading...")
        }
    }
    private fun updateDrinksListFail(error: String){
        binding.apply {
            dataImageView.setImageResource(R.drawable.ic_cancel)
            statusTextView.text=(error)
        }
    }
    private fun updateDrinksListSuccess(value: DrinkDetailModel)
    {
        binding.apply {
            dataImageView.visibility= View.GONE
            statusTextView.visibility=View.GONE
        }
        binding.recyclerViewDrinks.apply {
            //vertical by default
            layoutManager = LinearLayoutManager(context)
            adapter = RecyclerDrinkLayoutAdapter(value)
        }
    }

}

