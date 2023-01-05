package com.example.mahsimecommerce

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    private lateinit var retrofit: Retrofit
    private val BASE_URL = "https://parseapi.back4app.com/classes/"


    val instance: Api by lazy{
        retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(Api::class.java)
    }
}