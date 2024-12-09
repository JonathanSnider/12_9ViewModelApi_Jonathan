package com.example.apilearning

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apilearning.adapters.RecyclerDrinkLayoutAdapter
import com.example.apilearning.data.model.DrinkDetailModel
import com.example.apilearning.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModelInstance: MainViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        mainViewModelInstance = ViewModelProvider(this).get(MainViewModel::class.java)
        super.onCreate(savedInstanceState)
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
            println("VALUE: $value")
            //THE RESULT MUST BE SENT TO A SEPARATE FUNCTION TO ENSURE THE RESULT FROM THE
            //ASYNC FUNCTION CALL IS ACTUALLY RECEIVED BEFORE SUPPLYING IT TO THE RECYCLE VIEW
            updateDrinksList(value)

        }
        mainViewModelInstance.getDrinksList()


    }

    private fun updateDrinksList(value: DrinkDetailModel)
    {
        binding.recyclerViewDrinks.apply {
            //vertical by default
            layoutManager = LinearLayoutManager(context)
            adapter = RecyclerDrinkLayoutAdapter(value)
        }
    }
}

