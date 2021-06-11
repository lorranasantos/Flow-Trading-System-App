package com.example.flowtrandingsystem.gui.http

import com.example.flowtrandingsystem.gui.api.UrlApi.Companion.BASE_URL
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import com.example.flowtrandingsystem.gui.api.UrlApi.Companion.CONTENT_TYPE
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class HttpHelper {

    fun postCostumer  (json: String) : String{
        // Definir URL do servidor
        val URL = "${BASE_URL}costumer"

        // Definir o cabeçalho
        val headerhttp = "${CONTENT_TYPE}; charset=utf-8".toMediaTypeOrNull()

        // Criar um produto que vai disparar a requisição
        val produto = OkHttpClient()

        // Criar o body da requisição
        val body = json.toRequestBody(headerhttp)

        // Construir a requisição http para o servidor
        val request = Request.Builder().url(URL).post(body).build()

        // Utilizar o client para fazer a requisição e receber a resposta
        val response = produto.newCall(request).execute()

        return response.body.toString()
    }

    fun getProduct () {
        // Definir URL do servidor
        val URL = "${BASE_URL}product/"

        // Criar um produto que vai disparar a requisição
        val produto = OkHttpClient()

        // Criar uma requisição GET
        val request = Request.Builder().url(URL).get().build()

        // Enviar a requisição para o servidor
        val response = produto.newCall(request).execute()

        // Extrair o body da requisição
        val responseBody = response.body

        // Exibir o body da requisição
        if (responseBody != null){
            val json = responseBody.string()
            println("RESPOSTA ==========" + json)
        }
    }
}

