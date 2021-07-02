package com.example.flowtrandingsystem.gui.api

import com.example.flowtrandingsystem.gui.model.Logbook
import com.example.flowtrandingsystem.gui.model.PaymentMethod
import com.example.flowtrandingsystem.gui.model.Sale
import com.example.flowtrandingsystem.gui.model.Token
import retrofit2.Call
import retrofit2.http.*

interface SaleCalls {
    @POST("/sale")
    fun postSale(@Body sale: Sale, @Header("Authorization") token: String?) : Call<Sale>

    @GET("/paymentMethod")
    fun getLogPaymentMethod(@Header("Authorization") token: String?): Call<PaymentMethod>


}