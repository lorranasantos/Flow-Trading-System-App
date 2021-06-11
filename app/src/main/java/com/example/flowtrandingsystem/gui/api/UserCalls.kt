package com.example.flowtrandingsystem.gui.api

import com.example.flowtrandingsystem.gui.model.Token
import com.example.flowtrandingsystem.gui.model.User
import com.example.flowtrandingsystem.gui.model.UserLogin
import retrofit2.Call
import retrofit2.http.*


interface UserCalls {

    @GET("/user/find/{id}")
    fun getInfoFromUser(@Path("id") id: Int, @Header("Authorization") Token: String?): Call<User>

    @POST("/session")
    fun postLogin(@Body usuario: UserLogin) : Call<Token>

}