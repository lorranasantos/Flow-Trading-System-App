package com.example.flowtrandingsystem.gui.api

import com.example.flowtrandingsystem.gui.model.*
import retrofit2.Call
import retrofit2.http.*

interface SaleCalls {
    @POST("/sale")
    fun postSale(@Body sale: Sale, @Header("Authorization") token: String?) : Call<Sale>

    @GET("/branch/{branch}/inventory/report")
    fun getReportSales(@Path("branch") branch: Int, @Header("Authorization") token: String?): Call<List<ReportSale>>

    @GET("/branch/{branch}/sale")
    fun getSalesInfo(@Path("branch") branch: Int, @Header("Authorization") token: String?): Call<List<Sale>>

}