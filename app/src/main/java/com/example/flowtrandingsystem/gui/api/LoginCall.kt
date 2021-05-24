package com.example.flowtrandingsystem.gui.api

import com.example.flowtrandingsystem.gui.model.Token
import com.example.flowtrandingsystem.gui.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginCall {

    @POST("session")
    fun postLogin(@Body usuario: Usuario) : Call<Token>
}