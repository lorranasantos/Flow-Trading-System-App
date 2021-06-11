package com.example.flowtrandingsystem.gui.api


import com.example.flowtrandingsystem.gui.model.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductCalls {

    @GET("company/1/product")
    fun getProduto(): Call<List<Product>>

    @GET("product/barCode/{barCode}")
    fun getBarProduct(@Path("barCode") barCode: String): Call<Product>


}