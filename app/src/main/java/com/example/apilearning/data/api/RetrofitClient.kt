package com.example.apilearning.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    //thanks to dependency injection, this class is no longer needed

/*    //okHttpClient can be used to log responses from API calls. the BODY enum will
    //result in the body from the response being logged into Logcat
    //interceptors are more commonly used to perform BATCH actions like checking response
    //codes or attaching an auth token
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        //adding additional interceptors is optional for additional logging
*//*        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        })*//*
        .build()
    private val retrofitInstance = Retrofit.Builder()
        .baseUrl(APIDetails.BASE_URL)
        .client(okHttpClient) //customization and addition of properties like interceptors
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //class reference to the API Client interface and its functions
    val apiInstance = retrofitInstance.create(APIClient::class.java)*/
}