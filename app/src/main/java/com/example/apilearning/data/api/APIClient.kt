package com.example.apilearning.data.api

import com.example.apilearning.data.model.DrinkDetailModel
import retrofit2.http.GET

interface APIClient {
    @GET(APIDetails.ENDPOINT)
    suspend fun getDrinks(
        //if parameters were to be passed to the api call (like an ID) they would be placed here
    ):DrinkDetailModel
}