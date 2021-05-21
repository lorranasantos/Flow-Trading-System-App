package com.example.flowtrandingsystem.gui.api


import com.example.flowtrandingsystem.gui.model.Produto
import retrofit2.Call
import retrofit2.http.GET

interface ProdutosCall {

    @GET("product")

    fun getProduto(): Call<List<Produto>>

}