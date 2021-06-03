package com.example.flowtrandingsystem.gui.api

import com.example.flowtrandingsystem.gui.api.UrlApi.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {
    companion object {
        fun getRetrofit() : Retrofit {
            var retrofit = Retrofit.Builder()
<<<<<<< HEAD
                .baseUrl(Constants.BASE_URL)
=======
                .baseUrl(BASE_URL)
>>>>>>> feature/finalizacao_tela_pdv
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }
    }
}