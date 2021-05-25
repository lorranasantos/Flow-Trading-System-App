package com.example.flowtrandingsystem.gui.api

import com.example.flowtrandingsystem.gui.model.Produto
import com.example.flowtrandingsystem.gui.model.Token
import com.example.flowtrandingsystem.gui.model.Usuario
import com.example.flowtrandingsystem.gui.model.UsuarioInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface UserInfoCall {

    @GET("2")
    fun getInfoFromUser(): Call<UsuarioInfo>

}