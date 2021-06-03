package com.example.flowtrandingsystem.gui.api

import com.example.flowtrandingsystem.gui.model.Produto
import com.example.flowtrandingsystem.gui.model.Sale
import retrofit2.Call
import retrofit2.http.GET

interface SaleProductCall {

    @GET("itemPurchase")

    fun getSaleProduct(): Call<List<Sale>>
}