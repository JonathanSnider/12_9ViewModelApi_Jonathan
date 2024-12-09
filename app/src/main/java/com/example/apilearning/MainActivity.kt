package com.example.apilearning

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.apilearning.PetList.dogList
import com.example.apilearning.adapters.RecyclerDrinkLayoutAdapter
import com.example.apilearning.data.api.RetrofitClient
import com.example.apilearning.data.model.DrinkDetailModel
import com.example.apilearning.data.model.DrinkModel
import com.example.apilearning.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModelInstance: MainViewModel
    private lateinit var binding: ActivityMainBinding
    var retrievedDrinks=DrinkDetailModel()
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

        //mainViewModelInstance.getDrinksList()
        mainViewModelInstance.cocktails.observe(this) { value ->
            println("VALUE: $value")
            retrievedDrinks=(value)
            // or whatever update method you're using; or create a new adapter and reassign to recycler
        }
        binding.recyclerViewDrinks.apply {
            //vertical by default
            layoutManager = LinearLayoutManager(context)

            //println("RETRIVED DRINKS: ${retrievedDrinks.drinks?.get(0)}")
            adapter = RecyclerDrinkLayoutAdapter(retrievedDrinks)
        }

    }
}

object PetList {
    val dogList = listOf(
        "Husky",
        "German Shepard",
        "Chihuahua",
        "Golden Retriever",
        "Great Dane",
        "Borzoi",
        "Dachshund",
        "Husky",
        "German Shepard",
        "Chihuahua",
        "Golden Retriever",
        "Great Dane",
        "Borzoi",
        "Dachshund",
        "Husky",
        "German Shepard",
        "Chihuahua",
        "Golden Retriever",
        "Great Dane",
        "Borzoi",
        "Dachshund",
        "Dachshund",
        "Husky",
        "German Shepard",
        "Chihuahua",
        "Golden Retriever",
        "Great Dane",
        "Borzoi",
        "Dachshund",
        "Dachshund",
        "Husky",
        "German Shepard",
        "Chihuahua",
        "Golden Retriever",
        "Great Dane",
        "Borzoi",
        "Dachshund"
    )
}

