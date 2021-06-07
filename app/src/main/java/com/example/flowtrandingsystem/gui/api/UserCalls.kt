package com.example.flowtrandingsystem.gui.api

import com.example.flowtrandingsystem.gui.model.Token
import com.example.flowtrandingsystem.gui.model.User
import com.example.flowtrandingsystem.gui.model.UserLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserCalls {

    @GET("user")
    fun getInfoFromUser(@Body user: User): Call<User>

    @POST("session")
    fun postLogin(@Body usuario: UserLogin) : Call<Token>

}