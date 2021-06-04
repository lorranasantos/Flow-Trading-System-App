package com.example.flowtrandingsystem.gui.api

import com.google.gson.annotations.SerializedName

data class LoginRequest (

    @SerializedName("cnpj_ou_cpf")
    var cnpj_ou_cpf: String,

    @SerializedName("password")
    var password: String
)