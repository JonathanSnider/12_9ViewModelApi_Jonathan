package com.example.apilearning.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    //okHttpClient can be used to log responses from API calls. the BODY enum will
    //result in the body from the response being logged into Logcat
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
    private val retrofitInstance = Retrofit.Builder()
        .baseUrl(APIDetails.BASE_URL)
        .client(okHttpClient) //customization and addition of properties like interceptors
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //class reference to the API Client interface and its functions
    val apiInstance = retrofitInstance.create(APIClient::class.java)
}