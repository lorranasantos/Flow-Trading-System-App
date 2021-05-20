package com.example.flowtrandingsystem.gui.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {
    companion object {
        fun getRetrofit() : Retrofit {
            var retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3333/product")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit
        }
    }

}