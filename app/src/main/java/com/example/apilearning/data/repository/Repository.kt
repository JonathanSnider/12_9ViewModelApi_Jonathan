package com.example.apilearning.data.repository

import com.example.apilearning.data.model.DrinkDetailModel

interface Repository {
    //all possible network or local data access points
    suspend fun getDrinks(
        //if parameters were to be passed to the api call (like an ID) they would be placed here
    ): DrinkDetailModel
}