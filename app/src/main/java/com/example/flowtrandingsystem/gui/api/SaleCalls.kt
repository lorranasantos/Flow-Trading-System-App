package com.example.flowtrandingsystem.gui.api

import com.example.flowtrandingsystem.gui.model.*
import retrofit2.Call
import retrofit2.http.*

interface SaleCalls {
    @POST("/sale")
    fun postSale(@Body sale: Sale, @Header("Authorization") token: String?) : Call<Sale>

    @GET("/paymentMethod")
    fun getLogPaymentMethod(@Header("Authorization") token: String?): Call<PaymentMethod>

    @GET("/branch/1/inventory/report")
    fun getReportSales(@Header("Authorization") token: String?): Call<List<ReportSale>>
}