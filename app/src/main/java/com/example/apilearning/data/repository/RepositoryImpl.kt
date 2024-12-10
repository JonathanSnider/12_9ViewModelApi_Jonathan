package com.example.apilearning.data.repository

import com.example.apilearning.data.api.APIDetails
import com.example.apilearning.data.api.DrinkApiClient
import com.example.apilearning.data.model.DrinkDetailModel
import retrofit2.http.GET
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val drinkApiClient: DrinkApiClient
): Repository {
    @GET(APIDetails.ENDPOINT)
    override suspend fun getDrinks(): DrinkDetailModel {
        return drinkApiClient.getDrinks()
    }
}