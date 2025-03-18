package com.edofa.kotlinkalkulator.ApiByRetrofit.ApiService

import com.edofa.kotlinkalkulator.ApiByRetrofit.ApiModel.RequestApi
import com.edofa.kotlinkalkulator.ApiByRetrofit.ApiModel.ResponseApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServiceProduct {
    @GET("products")
    fun getProduct() : Call <ArrayList<ResponseApi>>

    @POST("products")
    fun createProduct(@Body product: RequestApi) : Call<ResponseApi>

    @DELETE("products/{id}")
    fun delateProduct(@Path("id") id : Int) : Call<ResponseBody>


}