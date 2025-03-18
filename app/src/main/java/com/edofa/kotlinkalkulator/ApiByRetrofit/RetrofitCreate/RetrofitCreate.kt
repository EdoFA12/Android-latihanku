package com.edofa.kotlinkalkulator.ApiByRetrofit.RetrofitCreate

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCreate {

    val baseUrl = "https://api.escuelajs.co/api/v1/"

    fun getInstance(): Retrofit{
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }
}