package com.example.apilearning.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofitInstance=Retrofit.Builder()
        .baseUrl(APIDetails.BASE_URL)
        //.client() //customization and addition of properties like interceptors
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    //class reference to the API Client interface and its functions
    val apiInstance= retrofitInstance.create(ApiClient::class.java)
}