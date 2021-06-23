package com.example.flowtrandingsystem.gui.api


import com.example.flowtrandingsystem.gui.model.Product
import com.example.flowtrandingsystem.gui.model.ProductAdapter
import com.example.flowtrandingsystem.gui.model.ProductType
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ProductCalls {

    @GET("/company/{companyId}/product")
    fun getProduct(@Path("companyId") companyId: Int, @Header("Authorization") token: String?): Call<List<Product>>

    @GET("/product/find/{id}")
    fun getProductById(@Path("id") id: Int, @Header("Authorization") token: String?): Call<Product>

    @GET("/product/barCode/{barCode}")
    fun getBarProduct(@Path("barCode") barCode: String, @Header("Authorization") token: String?): Call<ProductAdapter>

    @GET("/productType")
    fun getProductType(@Path("category") name: String , @Header("Authorization") token: String?): Call<ProductType>

}