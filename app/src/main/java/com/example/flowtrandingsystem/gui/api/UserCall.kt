package com.example.flowtrandingsystem.gui.api

import com.example.flowtrandingsystem.gui.model.Produto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserCall {

    @GET("")
    fun getBarProduct(@Path("barCode") barCode: String): Call<Produto>
}