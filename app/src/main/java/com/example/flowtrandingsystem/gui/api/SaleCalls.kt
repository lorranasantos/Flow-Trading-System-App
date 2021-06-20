package com.example.flowtrandingsystem.gui.api

import com.example.flowtrandingsystem.gui.model.Sale
import com.example.flowtrandingsystem.gui.model.Token
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface SaleCalls {
    @POST("/sale")
    fun postSale(@Body sale: Sale, @Header("Authorization") token: String?) : Call<Sale>

}