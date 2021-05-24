package com.example.flowtrandingsystem.gui.http

import com.example.flowtrandingsystem.gui.model.Usuario
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

class HttpHelper {

    fun login (cnpj_ou_cpf: String, password: String ) : Usuario {

        // Definir URL do servidor
        val URL = "http://10.0.2.2:3333/session"

        // Criar um usuario que vai disparar a requisição
        val cliente = OkHttpClient()

        // Construir a requisição http para o servidor
        var request = Request
            .Builder()
            .url(URL)
            .get()
            .build()

        // Enviar a requisição para o servidor
        val response = cliente.newCall(request).execute()

        // Extrair o body da requisição
        val responseBody = response.body

        var usuario = Usuario()

        // Criar um objeto usuario
        if(responseBody!!.contentLength().toInt() != 0) {
            var json = responseBody.string()
            var gson = Gson()

            usuario = gson.fromJson(json, Usuario::class.java)

        }

        return usuario
    }















    fun post (json: String) : String{

        // Definir URL do servidor
        val URL = "http://10.0.2.2:3333/product"

        // Definir o cabeçalho
        val headerhttp = "application/json; charset=utf-8".toMediaTypeOrNull()

        // Criar um produto que vai disparar a requisição
        val produto = OkHttpClient()

        // Criar o body da requisição
        val body = RequestBody.create(headerhttp, json)

        // Construir a requisição http para o servidor
        var request = Request.Builder().url(URL).post(body).build()

        // Utilizar o client para fazer a requisição e receber a resposta
        val response = produto.newCall(request).execute()

        return response.body.toString()
    }

    fun get () {
        // Definir URL do servidor
        val URL = "http://10.0.2.2:3333/product"

        // Criar um produto que vai disparar a requisição
        val produto = OkHttpClient()

        // Criar uma requisição GET
        val request = Request
            .Builder()
            .url(URL)
            .get()
            .build()

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

