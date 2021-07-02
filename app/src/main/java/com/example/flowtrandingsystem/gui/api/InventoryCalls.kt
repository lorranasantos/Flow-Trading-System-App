package com.example.flowtrandingsystem.gui.api

import com.example.flowtrandingsystem.gui.model.ProductAdapter
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface InventoryCalls {
    @GET("/branch/{branch}/inventory/report")
    fun getReports(@Path("branch") branch: Int, @Header("Authorization") token: String?): Call<ArrayList<ProductAdapter>>

}

