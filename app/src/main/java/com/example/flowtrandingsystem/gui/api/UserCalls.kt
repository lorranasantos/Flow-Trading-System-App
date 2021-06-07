package com.example.flowtrandingsystem.gui.api

import com.example.flowtrandingsystem.gui.model.Token
import com.example.flowtrandingsystem.gui.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserCalls {

    @GET("{id}")
    fun getInfoFromUser(): Call<User>

    @POST("session")
    fun postLogin(@Body usuario: User) : Call<Token>

}