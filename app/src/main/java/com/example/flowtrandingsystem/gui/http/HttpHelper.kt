package com.example.flowtrandingsystem.gui.http

import com.example.flowtrandingsystem.gui.api.UrlApi.Companion.BASE_URL
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

class HttpHelper {

    fun postCostumer  (json: String) : String{

        // Definir URL do servidor
        val URL = "http://10.0.2.2:3333/costumer"

        // Definir o cabeçalho
        val headerhttp = MediaType.parse("application/json; charset=utf-8")

        // Criar um produto que vai disparar a requisição
        val produto = OkHttpClient()

        // Criar o body da requisição
        val body = RequestBody.create(headerhttp, json)

        // Construir a requisição http para o servidor
        var request = Request.Builder().url(URL).post(body).build()

        // Utilizar o client para fazer a requisição e receber a resposta
        val response = produto.newCall(request).execute()

        return response.body()!!.string()
    }

    fun getInventoryProduct () {
        // Definir URL do servidor
        val URL = "http://10.0.2.2:3333/product"

        // Criar um produto que vai disparar a requisição
        val produto = OkHttpClient()

        // Criar uma requisição GET
        val request = Request.Builder().url(URL).get().build()

        // Enviar a requisição para o servidor
        val response = produto.newCall(request).execute()

        // Extrair o body da requisição
        val responseBody = response.body()

        // Exibir o body da requisição
        if (responseBody != null){
            val json = responseBody.string()
            println("RESPOSTA ==========" + json)
        }
    }

    fun getProductSale () {
        // Definir URL do servidor
        val URL = "http://10.0.2.2:3333/itemPurchase"

        // Criar um produto que vai disparar a requisição
        val produto = OkHttpClient()

        // Criar uma requisição GET
        val request = Request.Builder().url(URL).get().build()

        // Enviar a requisição para o servidor
        val response = produto.newCall(request).execute()

        // Extrair o body da requisição
        val responseBody = response.body()

        // Exibir o body da requisição
        if (responseBody != null){
            val json = responseBody.string()
            println("RESPOSTA TESTE ==========" + json)
        }
    }

}